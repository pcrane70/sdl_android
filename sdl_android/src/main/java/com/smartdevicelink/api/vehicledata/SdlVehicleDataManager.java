package com.smartdevicelink.api.vehicledata;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;

import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.api.permission.SdlPermissionEvent;
import com.smartdevicelink.api.permission.SdlPermissionFilter;
import com.smartdevicelink.api.permission.SdlPermissionListener;
import com.smartdevicelink.api.permission.SdlPermissionManager;
import com.smartdevicelink.proxy.rpc.OnVehicleData;

import java.util.EnumSet;
import java.util.concurrent.CopyOnWriteArrayList;

public class SdlVehicleDataManager {

    public static final int DEFAULT_TIMEOUT = 5 * 1000;

    public static final int PRIORITY_MIN = 0;
    public static final int PRIORITY_LOW = 1;
    public static final int PRIORITY_DEFAULT = 2;
    public static final int PRIORITY_HIGH = 3;
    public static final int PRIORITY_MAX = 4;

    private static final String TAG = SdlVehicleDataManager.class.getSimpleName();

    private final SparseArray<CopyOnWriteArrayList<SubscribeVehicleDataRecord>> mVehDataListenerMap = new SparseArray<>();
    private final SparseArray<SdlPermissionListener> mPermissionListenerRegistry = new SparseArray<>();
    private final Object LISTENER_LOCK = new Object();
    private final Object PERM_LOCK = new Object();
    private final VehicleDataInvoker mCommandInvoker;

    private final SdlPermissionManager mPermissionManager;
    private final SdlContext mSdlContext;

    public SdlVehicleDataManager(SdlContext context){
        mCommandInvoker = new VehicleDataInvoker();
        mPermissionManager = context.getSdlPermissionManager();
        mSdlContext = context;
    }

    /***
     * Subscribes to the vehicle data requested by {@param dataEnum}. The {@link SdlVehicleDataManager}
     * will provide callback events when the data is received to the {@param listener} provided. The
     * {@link SdlVehicleDataManager} handles permissions automatically for subscribing.
     * @param dataEnum Specifies the vehicle data being subscribed to
     * @param listener The callback for vehicle data received
     */
    public void subscribeVehicleData(@NonNull final SdlDataEnums dataEnum,
                                     @NonNull SdlVehicleDataListener listener){
        synchronized (LISTENER_LOCK){
            CopyOnWriteArrayList<SubscribeVehicleDataRecord> dataListeners = mVehDataListenerMap
                    .get(dataEnum.ordinal());
            if(dataEnum.getSubscribeVehicleDataRequestKeyName() == null){
                throw new RuntimeException("Vehicle data subscription is not supported for: "+ dataEnum);
            }
            //nothing has been listening for this data, therefore we should subscribe at this point
            if(dataListeners == null){
                dataListeners = new CopyOnWriteArrayList<>();
                mVehDataListenerMap.append(dataEnum.ordinal() , dataListeners);
                synchronized (PERM_LOCK){
                    final SubscribeVehicleDataCommand subCommand = new SubscribeVehicleDataCommand(
                            DEFAULT_TIMEOUT, PRIORITY_DEFAULT,
                            mSdlContext, true, EnumSet.of(dataEnum));
                    //check to see if the permission is already available
                    if(mPermissionManager.isPermissionAvailable(dataEnum.getSubVehicleDataPermission())){
                        Log.v(TAG,"Permissions available currently for "+ dataEnum.toString() + "." +
                                " Subscribing to vehicle data.");
                        mCommandInvoker.submitCommand(subCommand);
                    } else {
                        //adding a listener for when the permissions become available
                        SdlPermissionListener permissionListener = new SdlPermissionListener() {
                            @Override
                            public void onPermissionChanged(@NonNull SdlPermissionEvent event) {
                                synchronized (PERM_LOCK){
                                    parsePermissionEvent(dataEnum, event, subCommand, this);
                                }
                            }
                        };
                        mPermissionListenerRegistry.put(dataEnum.ordinal(), permissionListener);
                        SdlPermissionEvent initialEvent = mPermissionManager.addListener(permissionListener,
                                new SdlPermissionFilter(dataEnum.getSubVehicleDataPermission()));
                        //permissions could have changed between when we checked if they are available
                        //until when we added a listener
                        parsePermissionEvent(dataEnum, initialEvent, subCommand, permissionListener);
                    }
                }
            }
            SubscribeVehicleDataRecord newRecord = new SubscribeVehicleDataRecord(listener);
            if(!dataListeners.contains(newRecord)){
                Log.d(TAG, "Adding listener for: " + dataEnum);
                dataListeners.add(newRecord);
            }
        }
    }

    /***
     * Removes the {@param listener} and {@param dataEnum} combination from receiving vehicle data
     * events from the {@link SdlVehicleDataManager}.
     * @param dataEnum Vehicle data item used to originally subscribe the listener
     * @param listener The listener being removed from the {@link SdlVehicleDataManager}.
     */
    public void unsubscribeVehicleData(@NonNull SdlDataEnums dataEnum,
                                       @NonNull SdlVehicleDataListener listener){
        synchronized (LISTENER_LOCK){
            CopyOnWriteArrayList<SubscribeVehicleDataRecord> dataListeners = mVehDataListenerMap
                    .get(dataEnum.ordinal());
            if(dataEnum.getUnsubscribeVehicleDataRequestKeyName() == null){
                throw new RuntimeException("Vehicle data unsubscription is not supported for: " + dataEnum);
            }
            if(dataListeners == null){
                Log.w(TAG, "Unable to unsubscribe because there were no listeners subscribed to the data.");
                return;
            }
            int listenerIndex = dataListeners.indexOf(new SubscribeVehicleDataRecord(listener));
            if(listenerIndex >= 0) dataListeners.remove(listenerIndex).isValid = false;
            Log.d(TAG, "Removing listener for: " + dataEnum);
            if(dataListeners.isEmpty()){
                //nothing is listening for this data anymore, therefore we should unsubscribe
                synchronized (PERM_LOCK){
                    SdlPermissionListener permListener = mPermissionListenerRegistry.get(dataEnum.ordinal());
                    //if we don't have a permission listener for this vehicle data,
                    //that means we should be subscribed
                    if(permListener == null){
                        SubscribeVehicleDataCommand unSubCommand = new SubscribeVehicleDataCommand(
                                DEFAULT_TIMEOUT, PRIORITY_DEFAULT,
                                mSdlContext, false, EnumSet.of(dataEnum));
                        mCommandInvoker.submitCommand(unSubCommand);
                    } else {
                        //otherwise we are not subscribed and we should remove the listener
                        //so that we don't subscribe unnecessarily
                        mPermissionManager.removeListener(permListener);
                        mPermissionListenerRegistry.remove(dataEnum.ordinal());
                    }
                }
                mVehDataListenerMap.put(dataEnum.ordinal(), null);
            }
        }
    }

    /***
     * Requests the {@link SdlVehicleDataManager} to poll the requested {@param dataEnum} from
     * the vehicle. The result will be posted in the callback provided by {@param listener}.
     * @param dataEnum The vehicle data object to request from the vehicle
     * @param listener The callback where the requested data will be posted
     * @return {@literal True} if the request was able to be sent. {@literal False} otherwise.
     */
    public boolean pollVehicleData(@NonNull SdlDataEnums dataEnum,
                                   @NonNull VehicleDataCallback listener){
        if(mPermissionManager.isPermissionAvailable(dataEnum.getGetVehicleDataPermission())){
            GetVehicleDataCommand grabCommand = new GetVehicleDataCommand(
                    DEFAULT_TIMEOUT, PRIORITY_DEFAULT,
                    mSdlContext, dataEnum);
            grabCommand.setReadListener(listener);
            mCommandInvoker.submitCommand(grabCommand);
            return true;
        } else {
            return false;
        }
    }

    public void OnVehicleData(OnVehicleData data){
        synchronized (LISTENER_LOCK){
            //checking each of the data items to see what was updated in the data
            for(SdlDataEnums enums : SdlDataEnums.values()){
                String onVehicleDataKeyName = enums.getOnVehicleDataResponseKeyName();
                //potential that there are vehicle data items that are not able to be subscribed
                //to
                if(onVehicleDataKeyName != null){
                    Object potentialData = parseOnVehicleData(data, enums);
                    //utilizing null as a case for the data not coming back
                    if(potentialData != null){
                        CopyOnWriteArrayList<SubscribeVehicleDataRecord> listeners =
                                mVehDataListenerMap.get(enums.ordinal());
                        if(listeners != null){
                            for(SubscribeVehicleDataRecord listener : listeners){
                                if(listener.isValid){
                                    listener.vehicleDataListener.onDataChanged(potentialData);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void parsePermissionEvent(SdlDataEnums dataEnum, SdlPermissionEvent permissionEvent,
                                      VehicleDataCommand command, SdlPermissionListener listener){
        if(permissionEvent.getPermissionLevel() == SdlPermissionEvent.PermissionLevel.ALL
                && mPermissionListenerRegistry.get(dataEnum.ordinal()) != null){
            Log.v(TAG, "Permissions for "+ dataEnum.toString() + "are available now. " +
                    "Subscribing to vehicle data" );
            mCommandInvoker.submitCommand(command);
            mPermissionListenerRegistry.remove(dataEnum.ordinal());
            mPermissionManager.removeListener(listener);
        }
    }

    private final class SubscribeVehicleDataRecord{
        volatile boolean isValid = true;
        final SdlVehicleDataListener vehicleDataListener;

        SubscribeVehicleDataRecord(SdlVehicleDataListener listener){
            vehicleDataListener = listener;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SubscribeVehicleDataRecord that = (SubscribeVehicleDataRecord) o;

            return vehicleDataListener.equals(that.vehicleDataListener);

        }

        @Override
        public int hashCode() {
            return vehicleDataListener.hashCode();
        }
    }

    private Object parseOnVehicleData(OnVehicleData data, SdlDataEnums dataEnum){
        switch (dataEnum){
            case SPEED:
                return data.getSpeed();
            case RPM:
                return data.getRpm();
            case EXTERNAL_TEMPERATURE:
                return data.getExternalTemperature();
            case FUEL_LEVEL:
                return data.getFuelLevel();
            case PRNDL:
                return data.getPrndl();
            case TIRE_PRESSURE:
                return data.getTirePressure();
            case ENGINE_TORQUE:
                return data.getEngineTorque();
            case ODOMETER:
                return data.getOdometer();
            case GPS:
                return data.getGps();
            case FUEL_LEVEL_STATE:
                return data.getFuelLevelState();
            case INSTANT_FUEL_CONSUMPTION:
                return data.getInstantFuelConsumption();
            case BELT_STATUS:
                return data.getBeltStatus();
            case BODY_INFORMATION:
                return data.getBodyInformation();
            case DEVICE_STATUS:
                return data.getDeviceStatus();
            case DRIVER_BRAKING:
                return data.getDriverBraking();
            case WIPER_STATUS:
                return data.getWiperStatus();
            case HEAD_LAMP_STATUS:
                return data.getHeadLampStatus();
            case ACC_PEDAL_POSITION:
                return data.getAccPedalPosition();
            case STEERING_WHEEL_ANGLE:
                return data.getSteeringWheelAngle();
            case E_CALL_INFO:
                return data.getECallInfo();
            case AIRBAG_STATUS:
                return data.getAirbagStatus();
            case EMERGENCY_EVENT:
                return data.getEmergencyEvent();
            case CLUSTER_MODE_STATUS:
                return data.getClusterModeStatus();
            case MY_KEY:
                return data.getMyKey();
            default:
                return null;
        }
    }
}
