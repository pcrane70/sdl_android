package com.smartdevicelink.api.vehicledata;

import com.smartdevicelink.api.permission.SdlPermission;
import com.smartdevicelink.proxy.rpc.GetVehicleDataResponse;

public enum SdlDataEnums {

    SPEED(GetVehicleDataResponse.KEY_SPEED),
    RPM(GetVehicleDataResponse.KEY_RPM),
    EXTERNAL_TEMPERATURE(GetVehicleDataResponse.KEY_EXTERNAL_TEMPERATURE),
    FUEL_LEVEL(GetVehicleDataResponse.KEY_FUEL_LEVEL),
    VIN(GetVehicleDataResponse.KEY_VIN),
    PRNDL(GetVehicleDataResponse.KEY_PRNDL),
    TIRE_PRESSURE(GetVehicleDataResponse.KEY_TIRE_PRESSURE),
    ENGINE_TORQUE(GetVehicleDataResponse.KEY_ENGINE_TORQUE),
    ODOMETER(GetVehicleDataResponse.KEY_ODOMETER),
    GPS(GetVehicleDataResponse.KEY_GPS),
    FUEL_LEVEL_STATE(GetVehicleDataResponse.KEY_FUEL_LEVEL_STATE),
    INSTANT_FUEL_CONSUMPTION(GetVehicleDataResponse.KEY_INSTANT_FUEL_CONSUMPTION),
    BELT_STATUS(GetVehicleDataResponse.KEY_BELT_STATUS),
    BODY_INFORMATION(GetVehicleDataResponse.KEY_BODY_INFORMATION),
    DEVICE_STATUS(GetVehicleDataResponse.KEY_DEVICE_STATUS),
    DRIVER_BRAKING(GetVehicleDataResponse.KEY_DRIVER_BRAKING),
    WIPER_STATUS(GetVehicleDataResponse.KEY_WIPER_STATUS),
    HEAD_LAMP_STATUS(GetVehicleDataResponse.KEY_HEAD_LAMP_STATUS),
    ACC_PEDAL_POSITION(GetVehicleDataResponse.KEY_ACC_PEDAL_POSITION),
    STEERING_WHEEL_ANGLE(GetVehicleDataResponse.KEY_STEERING_WHEEL_ANGLE),
    E_CALL_INFO(GetVehicleDataResponse.KEY_E_CALL_INFO),
    AIRBAG_STATUS(GetVehicleDataResponse.KEY_AIRBAG_STATUS),
    EMERGENCY_EVENT(GetVehicleDataResponse.KEY_EMERGENCY_EVENT),
    CLUSTER_MODE_STATUS(GetVehicleDataResponse.KEY_CLUSTER_MODE_STATUS),
    MY_KEY(GetVehicleDataResponse.KEY_MY_KEY);


    SdlDataEnums(String keyName){
        mKeyName = keyName;
    }

    String mKeyName;
    SdlPermission mGetDataPerm;
    SdlPermission mSubDataPerm;
    SdlPermission mUnsubDataPerm;

    final String getKeyName(){
        return mKeyName;
    }
    final SdlPermission getGetVehicleDataPermission(){return  mGetDataPerm;}
    final SdlPermission getSubVehicleDataPermission(){return  mSubDataPerm;}
    final SdlPermission getUnsubVehicleDataPermission(){return  mUnsubDataPerm;}



    private void setGetVehicleDataPerm(SdlPermission getVehPerm){mGetDataPerm = getVehPerm;}
    private void setSubVehicleDataPerm(SdlPermission subVehPerm){mSubDataPerm = subVehPerm;}
    private void setUnsubVehicleDataPerm(SdlPermission unsubVehPerm){mUnsubDataPerm = unsubVehPerm;}


    static {
        SPEED.setGetVehicleDataPerm(SdlPermission.GetSpeed);
        RPM.setGetVehicleDataPerm(SdlPermission.GetRpm);
        EXTERNAL_TEMPERATURE.setGetVehicleDataPerm(SdlPermission.GetExternalTemperature);
        FUEL_LEVEL.setGetVehicleDataPerm(SdlPermission.GetFuelLevel);
        VIN.setGetVehicleDataPerm(SdlPermission.GetVin);
        PRNDL.setGetVehicleDataPerm(SdlPermission.GetPrndl);
        TIRE_PRESSURE.setGetVehicleDataPerm(SdlPermission.GetTirePressure);
        ENGINE_TORQUE.setGetVehicleDataPerm(SdlPermission.GetEngineTorque);
        ODOMETER.setGetVehicleDataPerm(SdlPermission.GetOdometer);
        GPS.setGetVehicleDataPerm(SdlPermission.GetGps);
        FUEL_LEVEL_STATE.setGetVehicleDataPerm(SdlPermission.GetFuelLevel_State);
        INSTANT_FUEL_CONSUMPTION.setGetVehicleDataPerm(SdlPermission.GetInstantFuelConsumption);
        BELT_STATUS.setGetVehicleDataPerm(SdlPermission.GetBeltStatus);
        BODY_INFORMATION.setGetVehicleDataPerm(SdlPermission.GetBodyInformation);
        DEVICE_STATUS.setGetVehicleDataPerm(SdlPermission.GetDeviceStatus);
        DRIVER_BRAKING.setGetVehicleDataPerm(SdlPermission.GetDriverBraking);
        WIPER_STATUS.setGetVehicleDataPerm(SdlPermission.GetWiperStatus);
        HEAD_LAMP_STATUS.setGetVehicleDataPerm(SdlPermission.GetHeadLampStatus);
        ACC_PEDAL_POSITION.setGetVehicleDataPerm(SdlPermission.GetAccPedalPosition);
        STEERING_WHEEL_ANGLE.setGetVehicleDataPerm(SdlPermission.GetSteeringWheelAngle);
        E_CALL_INFO.setGetVehicleDataPerm(SdlPermission.GetECallInfo);
        AIRBAG_STATUS.setGetVehicleDataPerm(SdlPermission.GetAirbagStatus);
        EMERGENCY_EVENT.setGetVehicleDataPerm(SdlPermission.GetEmergencyEvent);
        CLUSTER_MODE_STATUS.setGetVehicleDataPerm(SdlPermission.GetClusterModeStatus);
        MY_KEY.setGetVehicleDataPerm(SdlPermission.GetMyKey);

        SPEED.setSubVehicleDataPerm(SdlPermission.SubscribeSpeed);
        RPM.setSubVehicleDataPerm(SdlPermission.SubscribeRpm);
        EXTERNAL_TEMPERATURE.setSubVehicleDataPerm(SdlPermission.SubscribeExternalTemperature);
        FUEL_LEVEL.setSubVehicleDataPerm(SdlPermission.SubscribeFuelLevel);
        VIN.setSubVehicleDataPerm(SdlPermission.SubscribeVin);
        PRNDL.setSubVehicleDataPerm(SdlPermission.SubscribePrndl);
        TIRE_PRESSURE.setSubVehicleDataPerm(SdlPermission.SubscribeTirePressure);
        ENGINE_TORQUE.setSubVehicleDataPerm(SdlPermission.SubscribeEngineTorque);
        ODOMETER.setSubVehicleDataPerm(SdlPermission.SubscribeOdometer);
        GPS.setSubVehicleDataPerm(SdlPermission.SubscribeGps);
        FUEL_LEVEL_STATE.setSubVehicleDataPerm(SdlPermission.SubscribeFuelLevel_State);
        INSTANT_FUEL_CONSUMPTION.setSubVehicleDataPerm(SdlPermission.SubscribeInstantFuelConsumption);
        BELT_STATUS.setSubVehicleDataPerm(SdlPermission.SubscribeBeltStatus);
        BODY_INFORMATION.setSubVehicleDataPerm(SdlPermission.SubscribeBodyInformation);
        DEVICE_STATUS.setSubVehicleDataPerm(SdlPermission.SubscribeDeviceStatus);
        DRIVER_BRAKING.setSubVehicleDataPerm(SdlPermission.SubscribeDriverBraking);
        WIPER_STATUS.setSubVehicleDataPerm(SdlPermission.SubscribeWiperStatus);
        HEAD_LAMP_STATUS.setSubVehicleDataPerm(SdlPermission.SubscribeHeadLampStatus);
        ACC_PEDAL_POSITION.setSubVehicleDataPerm(SdlPermission.SubscribeAccPedalPosition);
        STEERING_WHEEL_ANGLE.setSubVehicleDataPerm(SdlPermission.SubscribeSteeringWheelAngle);
        E_CALL_INFO.setSubVehicleDataPerm(SdlPermission.SubscribeECallInfo);
        AIRBAG_STATUS.setSubVehicleDataPerm(SdlPermission.SubscribeAirbagStatus);
        EMERGENCY_EVENT.setSubVehicleDataPerm(SdlPermission.SubscribeEmergencyEvent);
        CLUSTER_MODE_STATUS.setSubVehicleDataPerm(SdlPermission.SubscribeClusterModeStatus);
        MY_KEY.setSubVehicleDataPerm(SdlPermission.SubscribeMyKey);

        SPEED.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeSpeed);
        RPM.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeRpm);
        EXTERNAL_TEMPERATURE.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeExternalTemperature);
        FUEL_LEVEL.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeFuelLevel);
        VIN.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeVin);
        PRNDL.setUnsubVehicleDataPerm(SdlPermission.UnsubscribePrndl);
        TIRE_PRESSURE.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeTirePressure);
        ENGINE_TORQUE.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeEngineTorque);
        ODOMETER.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeOdometer);
        GPS.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeGps);
        FUEL_LEVEL_STATE.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeFuelLevel_State);
        INSTANT_FUEL_CONSUMPTION.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeInstantFuelConsumption);
        BELT_STATUS.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeBeltStatus);
        BODY_INFORMATION.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeBodyInformation);
        DEVICE_STATUS.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeDeviceStatus);
        DRIVER_BRAKING.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeDriverBraking);
        WIPER_STATUS.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeWiperStatus);
        HEAD_LAMP_STATUS.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeHeadLampStatus);
        ACC_PEDAL_POSITION.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeAccPedalPosition);
        STEERING_WHEEL_ANGLE.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeSteeringWheelAngle);
        E_CALL_INFO.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeECallInfo);
        AIRBAG_STATUS.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeAirbagStatus);
        EMERGENCY_EVENT.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeEmergencyEvent);
        CLUSTER_MODE_STATUS.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeClusterModeStatus);
        MY_KEY.setUnsubVehicleDataPerm(SdlPermission.UnsubscribeMyKey);
    }
}
