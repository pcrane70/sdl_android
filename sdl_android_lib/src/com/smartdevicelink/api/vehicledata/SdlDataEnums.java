package com.smartdevicelink.api.vehicledata;

import com.smartdevicelink.api.permission.SdlPermission;
import com.smartdevicelink.proxy.rpc.GetVehicleDataResponse;

public enum SdlDataEnums {

    SPEED(GetVehicleDataResponse.KEY_SPEED, SdlPermission.GetSpeed, SdlPermission.SubscribeSpeed),
    RPM(GetVehicleDataResponse.KEY_RPM, SdlPermission.GetRpm, SdlPermission.SubscribeRpm),
    EXTERNAL_TEMPERATURE(GetVehicleDataResponse.KEY_EXTERNAL_TEMPERATURE, SdlPermission.GetExternalTemperature, SdlPermission.SubscribeExternalTemperature),
    FUEL_LEVEL(GetVehicleDataResponse.KEY_FUEL_LEVEL, SdlPermission.GetFuelLevel, SdlPermission.SubscribeFuelLevel),
    VIN(GetVehicleDataResponse.KEY_VIN, SdlPermission.GetVin, null),
    PRNDL(GetVehicleDataResponse.KEY_PRNDL, SdlPermission.GetPrndl, SdlPermission.SubscribePrndl),
    TIRE_PRESSURE(GetVehicleDataResponse.KEY_TIRE_PRESSURE, SdlPermission.GetTirePressure, SdlPermission.SubscribeTirePressure),
    ENGINE_TORQUE(GetVehicleDataResponse.KEY_ENGINE_TORQUE, SdlPermission.GetEngineTorque, SdlPermission.SubscribeEngineTorque),
    ODOMETER(GetVehicleDataResponse.KEY_ODOMETER, SdlPermission.GetOdometer, SdlPermission.SubscribeOdometer),
    GPS(GetVehicleDataResponse.KEY_GPS, SdlPermission.GetGps, SdlPermission.SubscribeGps),
    FUEL_LEVEL_STATE(GetVehicleDataResponse.KEY_FUEL_LEVEL_STATE, SdlPermission.GetFuelLevel_State, SdlPermission.SubscribeFuelLevel_State),
    INSTANT_FUEL_CONSUMPTION(GetVehicleDataResponse.KEY_INSTANT_FUEL_CONSUMPTION, SdlPermission.GetInstantFuelConsumption, SdlPermission.SubscribeInstantFuelConsumption),
    BELT_STATUS(GetVehicleDataResponse.KEY_BELT_STATUS, SdlPermission.GetBeltStatus, SdlPermission.SubscribeBeltStatus),
    BODY_INFORMATION(GetVehicleDataResponse.KEY_BODY_INFORMATION, SdlPermission.GetBodyInformation, SdlPermission.SubscribeBodyInformation),
    DEVICE_STATUS(GetVehicleDataResponse.KEY_DEVICE_STATUS, SdlPermission.GetDeviceStatus, SdlPermission.SubscribeDeviceStatus),
    DRIVER_BRAKING(GetVehicleDataResponse.KEY_DRIVER_BRAKING, SdlPermission.GetDriverBraking, SdlPermission.SubscribeDriverBraking),
    WIPER_STATUS(GetVehicleDataResponse.KEY_WIPER_STATUS, SdlPermission.GetWiperStatus, SdlPermission.SubscribeWiperStatus),
    HEAD_LAMP_STATUS(GetVehicleDataResponse.KEY_HEAD_LAMP_STATUS, SdlPermission.GetHeadLampStatus, SdlPermission.SubscribeWiperStatus),
    ACC_PEDAL_POSITION(GetVehicleDataResponse.KEY_ACC_PEDAL_POSITION, SdlPermission.GetAccPedalPosition, SdlPermission.SubscribeAccPedalPosition),
    STEERING_WHEEL_ANGLE(GetVehicleDataResponse.KEY_STEERING_WHEEL_ANGLE, SdlPermission.GetSteeringWheelAngle, SdlPermission.SubscribeSteeringWheelAngle),
    E_CALL_INFO(GetVehicleDataResponse.KEY_E_CALL_INFO, SdlPermission.GetECallInfo, SdlPermission.SubscribeECallInfo),
    AIRBAG_STATUS(GetVehicleDataResponse.KEY_AIRBAG_STATUS, SdlPermission.GetAirbagStatus, SdlPermission.SubscribeAirbagStatus),
    EMERGENCY_EVENT(GetVehicleDataResponse.KEY_EMERGENCY_EVENT, SdlPermission.GetEmergencyEvent, SdlPermission.SubscribeEmergencyEvent),
    CLUSTER_MODE_STATUS(GetVehicleDataResponse.KEY_CLUSTER_MODE_STATUS, SdlPermission.GetClusterModeStatus, SdlPermission.SubscribeClusterModeStatus),
    MY_KEY(GetVehicleDataResponse.KEY_MY_KEY, SdlPermission.GetMyKey, SdlPermission.SubscribeMyKey);

    SdlDataEnums(String keyName, SdlPermission getVehPermission, SdlPermission subVehPermission){
        mKeyName = keyName;
        mGetDataPerm = getVehPermission;
        mSubDataPerm = subVehPermission;
    }

    private final String mKeyName;
    private final SdlPermission mGetDataPerm;
    private final SdlPermission mSubDataPerm;

    String getKeyName(){
        return mKeyName;
    }
    SdlPermission getGetVehicleDataPermission(){return  mGetDataPerm;}
    SdlPermission getSubVehicleDataPermission(){return  mSubDataPerm;}

}
