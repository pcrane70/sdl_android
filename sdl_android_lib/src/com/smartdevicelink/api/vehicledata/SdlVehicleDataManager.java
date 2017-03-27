package com.smartdevicelink.api.vehicledata;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;

import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.api.permission.SdlPermissionEvent;
import com.smartdevicelink.api.permission.SdlPermissionFilter;
import com.smartdevicelink.api.permission.SdlPermissionListener;
import com.smartdevicelink.api.permission.SdlPermissionManager;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.OnVehicleData;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class SdlVehicleDataManager {

    public static final int DEFAULT_TIMEOUT = 5 * 1000;

    public static final int PRIORITY_MIN = 0;
    public static final int PRIORITY_LOW = 1;
    public static final int PRIORITY_DEFAULT = 2;
    public static final int PRIORITY_HIGH = 3;
    public static final int PRIORITY_MAX = 4;

    private static final String TAG = SdlVehicleDataManager.class.getSimpleName();

    private final HashMap<SdlDataEnums, Object> mCurrentData = new HashMap<>();
    private final SparseArray<CopyOnWriteArrayList<SdlVehicleDataListener>> mVehDataListenerMap = new SparseArray<>();
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

    public void subscribeVehicleData(@NonNull final SdlDataEnums dataEnum,
                                     @NonNull SdlVehicleDataListener listener){
        synchronized (LISTENER_LOCK){
            CopyOnWriteArrayList<SdlVehicleDataListener> dataListeners = mVehDataListenerMap.get(dataEnum.ordinal());
            if(dataEnum == SdlDataEnums.VIN){
                throw new RuntimeException("VIN is not supported for Vehicle data subscription");
            }
            if(dataListeners == null){
                dataListeners = new CopyOnWriteArrayList<>();
                mVehDataListenerMap.append(dataEnum.ordinal() , dataListeners);
            }
            if(dataListeners.isEmpty()){
                subscribePermissions(dataEnum);
            }
            dataListeners.add(listener);
        }
    }

    public void unsubscribeVehicleData(@NonNull SdlDataEnums dataEnum,
                                       @NonNull SdlVehicleDataListener listener){
        synchronized (LISTENER_LOCK){
            CopyOnWriteArrayList<SdlVehicleDataListener> dataListeners = mVehDataListenerMap.get(dataEnum.ordinal());
            if(dataEnum == SdlDataEnums.VIN){
                throw new RuntimeException("VIN is not supported for Vehicle data unsubscription");
            }
            if(dataListeners == null || dataListeners.isEmpty()){
                Log.w(TAG, "Unable to unsubscribe because there were no listeners subscribed to the data.");
                return;
            }
            boolean listenerRemoved = dataListeners.remove(listener);
            if(dataListeners.isEmpty() && listenerRemoved){
                unsubscribePermissions(dataEnum);
            }
        }
    }

    public boolean pullVehicleData(@NonNull SdlDataEnums dataEnum, GetVehicleDataListener listener){
        if(mPermissionManager.isPermissionAvailable(dataEnum.getGetVehicleDataPermission())){
            GetVehicleDataCommand grabCommand = new GetVehicleDataCommand(DEFAULT_TIMEOUT, PRIORITY_DEFAULT,
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
            for(SdlDataEnums enums : updateData(data)){
                if(mVehDataListenerMap.indexOfKey(enums.ordinal()) != -1){
                    for(SdlVehicleDataListener listener : mVehDataListenerMap.get(enums.ordinal())){
                        listener.onDataChanged(mCurrentData.get(enums));
                    }
                }
            }
        }
    }

    private EnumSet<SdlDataEnums> updateData(RPCMessage data){
        EnumSet<SdlDataEnums> changeSet = EnumSet.noneOf(SdlDataEnums.class);
        for(SdlDataEnums enums : SdlDataEnums.values()){
            Object potentialData = data.getParameters(enums.getKeyName());
            if(potentialData != null){
                mCurrentData.put(enums , potentialData);
                changeSet.add(enums);
            }
        }
        return changeSet;
    }

    private void subscribePermissions(final SdlDataEnums dataEnum){
        synchronized (PERM_LOCK){
            final SubscribeVehicleDataCommand subCommand = new SubscribeVehicleDataCommand(DEFAULT_TIMEOUT, PRIORITY_DEFAULT,
                    mSdlContext, true, EnumSet.of(dataEnum));
            if(mPermissionManager.isPermissionAvailable(dataEnum.getSubVehicleDataPermission())){
                mCommandInvoker.submitCommand(subCommand);
            } else {
                SdlPermissionListener permissionListener = new SdlPermissionListener() {
                    @Override
                    public void onPermissionChanged(@NonNull SdlPermissionEvent event) {
                        synchronized (PERM_LOCK){
                            if(event.getPermissionLevel() == SdlPermissionEvent.PermissionLevel.ALL
                                    && mPermissionListenerRegistry.get(dataEnum.ordinal()) != null){
                                Log.v(TAG, "Permission became available, we can now sub");
                                mCommandInvoker.submitCommand(subCommand);
                                mPermissionListenerRegistry.remove(dataEnum.ordinal());
                                mPermissionManager.removeListener(this);
                            }
                        }
                    }
                };
                mPermissionListenerRegistry.put(dataEnum.ordinal(), permissionListener);
                mPermissionManager.addListener(permissionListener,
                        new SdlPermissionFilter(dataEnum.getSubVehicleDataPermission()));
            }
        }
    }

    private void unsubscribePermissions(final SdlDataEnums dataEnum){
        synchronized (PERM_LOCK){
            SdlPermissionListener permListener = mPermissionListenerRegistry.get(dataEnum.ordinal());
            if(permListener == null){
                SubscribeVehicleDataCommand unSubCommand = new SubscribeVehicleDataCommand(DEFAULT_TIMEOUT, PRIORITY_DEFAULT,
                        mSdlContext, false, EnumSet.of(dataEnum));
                mCommandInvoker.submitCommand(unSubCommand);
            } else {
                mPermissionManager.removeListener(permListener);
                mPermissionListenerRegistry.remove(dataEnum.ordinal());
            }
        }
    }


}
