package com.smartdevicelink.api.vehicledata;

import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.proxy.RPCResponse;
import com.smartdevicelink.proxy.rpc.GetVehicleData;
import com.smartdevicelink.proxy.rpc.GetVehicleDataResponse;
import com.smartdevicelink.proxy.rpc.listeners.OnRPCResponseListener;

public class GetVehicleDataCommand extends VehicleDataCommand {

    private final SdlDataEnums mGetDataEnum;
    private VehicleDataCallback mGetVehReadListener;

    GetVehicleDataCommand(int timeout, int priority,
                          SdlContext sdlContext, SdlDataEnums vehDataEnum){
        super(timeout, priority, sdlContext);
        mGetDataEnum = vehDataEnum;
    }

    void setReadListener(VehicleDataCallback listener){
        mGetVehReadListener = listener;
    }


    @Override
    public void execute(final CompletionListener listener) {
        final GetVehicleData getVehDataRPC = new GetVehicleData();
        getVehDataRPC.setParameters(mGetDataEnum.getVehicleDataRequestKeyName(), true);
        getVehDataRPC.setOnRPCResponseListener(new OnRPCResponseListener() {
            @Override
            public void onResponse(int correlationId, RPCResponse response) {
                if(mGetVehReadListener != null){
                    GetVehicleDataResponse vehDataResponse = (GetVehicleDataResponse) response;
                    mGetVehReadListener.onReadComplete(parseGetVehicleData(vehDataResponse,
                            mGetDataEnum));
                }
                listener.onComplete();
            }
        });
        mSdlHandler.post(new Runnable() {
            @Override
            public void run() {
                mSdlContext.sendRpc(getVehDataRPC);
            }
        });
    }

    @Override
    public void stop() {
        if(mGetVehReadListener != null){
            mSdlHandler.post(new Runnable() {
                @Override
                public void run() {
                    mGetVehReadListener.onCanceled();
                }
            });

        }
    }

    @Override
    public void onTimeout() {
        if(mGetVehReadListener != null){
            mSdlHandler.post(new Runnable() {
                @Override
                public void run() {
                    mGetVehReadListener.onTimeout();
                }
            });

        }
    }

    private Object parseGetVehicleData(GetVehicleDataResponse data, SdlDataEnums dataEnum){
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
