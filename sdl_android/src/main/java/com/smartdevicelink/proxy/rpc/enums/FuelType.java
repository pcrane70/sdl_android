package com.smartdevicelink.proxy.rpc.enums;

public enum FuelType {
    GASOLINE,
    DIESEL,
    CNG,
    LPG,
    HYDROGEN,
    BATTERY,
    ;

    public static FuelType valueForString(String value) {
        try{
            return valueOf(value);
        }catch(Exception e){
            return null;
        }
    }
}
