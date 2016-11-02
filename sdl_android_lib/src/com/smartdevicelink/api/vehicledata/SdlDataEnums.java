package com.smartdevicelink.api.vehicledata;

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

    public final String getKeyName(){
        return mKeyName;
    }

}
