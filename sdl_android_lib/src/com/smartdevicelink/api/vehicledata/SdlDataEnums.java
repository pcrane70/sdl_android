package com.smartdevicelink.api.vehicledata;

import com.smartdevicelink.api.permission.SdlPermission;
import com.smartdevicelink.proxy.rpc.GetVehicleData;
import com.smartdevicelink.proxy.rpc.GetVehicleDataResponse;
import com.smartdevicelink.proxy.rpc.OnVehicleData;
import com.smartdevicelink.proxy.rpc.SubscribeVehicleData;
import com.smartdevicelink.proxy.rpc.UnsubscribeVehicleData;

public enum SdlDataEnums {

    SPEED(GetVehicleDataResponse.KEY_SPEED, GetVehicleData.KEY_SPEED, SubscribeVehicleData.KEY_SPEED,
            UnsubscribeVehicleData.KEY_SPEED, OnVehicleData.KEY_SPEED, SdlPermission.GetSpeed,
            SdlPermission.SubscribeSpeed),
    RPM(GetVehicleDataResponse.KEY_RPM, GetVehicleData.KEY_RPM, SubscribeVehicleData.KEY_RPM,
            UnsubscribeVehicleData.KEY_RPM, OnVehicleData.KEY_RPM, SdlPermission.GetRpm,
            SdlPermission.SubscribeRpm),
    EXTERNAL_TEMPERATURE(GetVehicleDataResponse.KEY_EXTERNAL_TEMPERATURE,
            GetVehicleData.KEY_EXTERNAL_TEMPERATURE, SubscribeVehicleData.KEY_EXTERNAL_TEMPERATURE,
            UnsubscribeVehicleData.KEY_EXTERNAL_TEMPERATURE, OnVehicleData.KEY_EXTERNAL_TEMPERATURE,
            SdlPermission.GetExternalTemperature, SdlPermission.SubscribeExternalTemperature),
    FUEL_LEVEL(GetVehicleDataResponse.KEY_FUEL_LEVEL,GetVehicleData.KEY_FUEL_LEVEL,
            SubscribeVehicleData.KEY_FUEL_LEVEL, UnsubscribeVehicleData.KEY_FUEL_LEVEL,
            OnVehicleData.KEY_FUEL_LEVEL, SdlPermission.GetFuelLevel,
            SdlPermission.SubscribeFuelLevel),
    VIN(GetVehicleDataResponse.KEY_VIN, GetVehicleData.KEY_VIN, null, null, null,
            SdlPermission.GetVin, null),
    PRNDL(GetVehicleDataResponse.KEY_PRNDL, GetVehicleData.KEY_PRNDL,
            SubscribeVehicleData.KEY_PRNDL, UnsubscribeVehicleData.KEY_PRNDL,
            OnVehicleData.KEY_PRNDL, SdlPermission.GetPrndl, SdlPermission.SubscribePrndl),
    TIRE_PRESSURE(GetVehicleDataResponse.KEY_TIRE_PRESSURE, GetVehicleData.KEY_TIRE_PRESSURE,
            SubscribeVehicleData.KEY_TIRE_PRESSURE, UnsubscribeVehicleData.KEY_TIRE_PRESSURE,
            OnVehicleData.KEY_TIRE_PRESSURE, SdlPermission.GetTirePressure,
            SdlPermission.SubscribeTirePressure),
    ENGINE_TORQUE(GetVehicleDataResponse.KEY_ENGINE_TORQUE, GetVehicleData.KEY_ENGINE_TORQUE,
            SubscribeVehicleData.KEY_ENGINE_TORQUE, UnsubscribeVehicleData.KEY_ENGINE_TORQUE,
            OnVehicleData.KEY_ENGINE_TORQUE, SdlPermission.GetEngineTorque,
            SdlPermission.SubscribeEngineTorque),
    ODOMETER(GetVehicleDataResponse.KEY_ODOMETER, GetVehicleData.KEY_ODOMETER,
            SubscribeVehicleData.KEY_ODOMETER, UnsubscribeVehicleData.KEY_ODOMETER,
            OnVehicleData.KEY_ODOMETER, SdlPermission.GetOdometer, SdlPermission.SubscribeOdometer),
    GPS(GetVehicleDataResponse.KEY_GPS, GetVehicleData.KEY_GPS, SubscribeVehicleData.KEY_GPS,
            UnsubscribeVehicleData.KEY_GPS, OnVehicleData.KEY_GPS, SdlPermission.GetGps,
            SdlPermission.SubscribeGps),
    FUEL_LEVEL_STATE(GetVehicleDataResponse.KEY_FUEL_LEVEL_STATE,
            GetVehicleData.KEY_FUEL_LEVEL_STATE, SubscribeVehicleData.KEY_FUEL_LEVEL_STATE,
            UnsubscribeVehicleData.KEY_FUEL_LEVEL_STATE, OnVehicleData.KEY_FUEL_LEVEL_STATE,
            SdlPermission.GetFuelLevel_State, SdlPermission.SubscribeFuelLevel_State),
    INSTANT_FUEL_CONSUMPTION(GetVehicleDataResponse.KEY_INSTANT_FUEL_CONSUMPTION,
            GetVehicleData.KEY_INSTANT_FUEL_CONSUMPTION,
            SubscribeVehicleData.KEY_INSTANT_FUEL_CONSUMPTION,
            UnsubscribeVehicleData.KEY_INSTANT_FUEL_CONSUMPTION,
            OnVehicleData.KEY_INSTANT_FUEL_CONSUMPTION, SdlPermission.GetInstantFuelConsumption,
            SdlPermission.SubscribeInstantFuelConsumption),
    BELT_STATUS(GetVehicleDataResponse.KEY_BELT_STATUS, GetVehicleData.KEY_BELT_STATUS,
            SubscribeVehicleData.KEY_BELT_STATUS, UnsubscribeVehicleData.KEY_BELT_STATUS,
            OnVehicleData.KEY_BELT_STATUS, SdlPermission.GetBeltStatus,
            SdlPermission.SubscribeBeltStatus),
    BODY_INFORMATION(GetVehicleDataResponse.KEY_BODY_INFORMATION,
            GetVehicleData.KEY_BODY_INFORMATION, SubscribeVehicleData.KEY_BODY_INFORMATION,
            UnsubscribeVehicleData.KEY_BODY_INFORMATION, OnVehicleData.KEY_BODY_INFORMATION,
            SdlPermission.GetBodyInformation, SdlPermission.SubscribeBodyInformation),
    DEVICE_STATUS(GetVehicleDataResponse.KEY_DEVICE_STATUS, GetVehicleData.KEY_DEVICE_STATUS,
            SubscribeVehicleData.KEY_DEVICE_STATUS, UnsubscribeVehicleData.KEY_DEVICE_STATUS,
            OnVehicleData.KEY_DEVICE_STATUS, SdlPermission.GetDeviceStatus,
            SdlPermission.SubscribeDeviceStatus),
    DRIVER_BRAKING(GetVehicleDataResponse.KEY_DRIVER_BRAKING, GetVehicleData.KEY_DRIVER_BRAKING,
            SubscribeVehicleData.KEY_DRIVER_BRAKING, UnsubscribeVehicleData.KEY_DRIVER_BRAKING,
            OnVehicleData.KEY_DRIVER_BRAKING, SdlPermission.GetDriverBraking,
            SdlPermission.SubscribeDriverBraking),
    WIPER_STATUS(GetVehicleDataResponse.KEY_WIPER_STATUS, GetVehicleData.KEY_WIPER_STATUS,
            SubscribeVehicleData.KEY_WIPER_STATUS, UnsubscribeVehicleData.KEY_WIPER_STATUS,
            OnVehicleData.KEY_WIPER_STATUS, SdlPermission.GetWiperStatus,
            SdlPermission.SubscribeWiperStatus),
    HEAD_LAMP_STATUS(GetVehicleDataResponse.KEY_HEAD_LAMP_STATUS,
            GetVehicleData.KEY_HEAD_LAMP_STATUS, SubscribeVehicleData.KEY_HEAD_LAMP_STATUS,
            UnsubscribeVehicleData.KEY_HEAD_LAMP_STATUS, OnVehicleData.KEY_HEAD_LAMP_STATUS,
            SdlPermission.GetHeadLampStatus, SdlPermission.SubscribeWiperStatus),
    ACC_PEDAL_POSITION(GetVehicleDataResponse.KEY_ACC_PEDAL_POSITION,
            GetVehicleData.KEY_ACC_PEDAL_POSITION, SubscribeVehicleData.KEY_ACC_PEDAL_POSITION,
            UnsubscribeVehicleData.KEY_ACC_PEDAL_POSITION, OnVehicleData.KEY_ACC_PEDAL_POSITION,
            SdlPermission.GetAccPedalPosition, SdlPermission.SubscribeAccPedalPosition),
    STEERING_WHEEL_ANGLE(GetVehicleDataResponse.KEY_STEERING_WHEEL_ANGLE,
            GetVehicleData.KEY_STEERING_WHEEL_ANGLE, SubscribeVehicleData.KEY_STEERING_WHEEL_ANGLE,
            UnsubscribeVehicleData.KEY_STEERING_WHEEL_ANGLE, OnVehicleData.KEY_STEERING_WHEEL_ANGLE,
            SdlPermission.GetSteeringWheelAngle, SdlPermission.SubscribeSteeringWheelAngle),
    E_CALL_INFO(GetVehicleDataResponse.KEY_E_CALL_INFO, GetVehicleData.KEY_E_CALL_INFO,
            SubscribeVehicleData.KEY_E_CALL_INFO, UnsubscribeVehicleData.KEY_E_CALL_INFO,
            OnVehicleData.KEY_E_CALL_INFO, SdlPermission.GetECallInfo,
            SdlPermission.SubscribeECallInfo),
    AIRBAG_STATUS(GetVehicleDataResponse.KEY_AIRBAG_STATUS, GetVehicleData.KEY_AIRBAG_STATUS,
            SubscribeVehicleData.KEY_AIRBAG_STATUS, UnsubscribeVehicleData.KEY_AIRBAG_STATUS,
            OnVehicleData.KEY_AIRBAG_STATUS, SdlPermission.GetAirbagStatus,
            SdlPermission.SubscribeAirbagStatus),
    EMERGENCY_EVENT(GetVehicleDataResponse.KEY_EMERGENCY_EVENT, GetVehicleData.KEY_EMERGENCY_EVENT,
            SubscribeVehicleData.KEY_EMERGENCY_EVENT, UnsubscribeVehicleData.KEY_EMERGENCY_EVENT,
            OnVehicleData.KEY_EMERGENCY_EVENT, SdlPermission.GetEmergencyEvent,
            SdlPermission.SubscribeEmergencyEvent),
    CLUSTER_MODE_STATUS(GetVehicleDataResponse.KEY_CLUSTER_MODE_STATUS,
            GetVehicleData.KEY_CLUSTER_MODE_STATUS, SubscribeVehicleData.KEY_CLUSTER_MODE_STATUS,
            UnsubscribeVehicleData.KEY_CLUSTER_MODE_STATUS, OnVehicleData.KEY_CLUSTER_MODE_STATUS,
            SdlPermission.GetClusterModeStatus, SdlPermission.SubscribeClusterModeStatus),
    MY_KEY(GetVehicleDataResponse.KEY_MY_KEY, GetVehicleData.KEY_MY_KEY,
            SubscribeVehicleData.KEY_MY_KEY, UnsubscribeVehicleData.KEY_MY_KEY,
            OnVehicleData.KEY_MY_KEY, SdlPermission.GetMyKey, SdlPermission.SubscribeMyKey);

    SdlDataEnums(String getVehResponseKeyName, String getVehDataRequestKeyName,
                 String subscribeVehDataRequestKeyName, String unsubscribeVehDataRequestKeyName,
                 String onVehResponseKeyName, SdlPermission getVehPermission,
                 SdlPermission subVehPermission){
        mGetVehicleDataResponseKeyName = getVehResponseKeyName;
        mGetVehicleDataRequestKeyName = getVehDataRequestKeyName;
        mSubscribeVehicleDataRequestKeyName = subscribeVehDataRequestKeyName;
        mUnsubscribeVehicleDataRequestKeyName = unsubscribeVehDataRequestKeyName;
        mOnVehicleDataResponseKeyName = onVehResponseKeyName;
        mGetDataPerm = getVehPermission;
        mSubDataPerm = subVehPermission;
    }

    private final String mGetVehicleDataResponseKeyName;
    private final String mGetVehicleDataRequestKeyName;
    private final String mSubscribeVehicleDataRequestKeyName;
    private final String mUnsubscribeVehicleDataRequestKeyName;
    private final String mOnVehicleDataResponseKeyName;
    private final SdlPermission mGetDataPerm;
    private final SdlPermission mSubDataPerm;

    String getVehicleDataResponseKeyName(){
        return mGetVehicleDataResponseKeyName;
    }

    String getVehicleDataRequestKeyName() {
        return mGetVehicleDataRequestKeyName;
    }

    String getSubscribeVehicleDataRequestKeyName() {
        return mSubscribeVehicleDataRequestKeyName;
    }

    String getUnsubscribeVehicleDataRequestKeyName() {
        return mUnsubscribeVehicleDataRequestKeyName;
    }

    String getOnVehicleDataResponseKeyName() {
        return mOnVehicleDataResponseKeyName;
    }

    SdlPermission getGetVehicleDataPermission(){return  mGetDataPerm;}
    SdlPermission getSubVehicleDataPermission(){return  mSubDataPerm;}

}
