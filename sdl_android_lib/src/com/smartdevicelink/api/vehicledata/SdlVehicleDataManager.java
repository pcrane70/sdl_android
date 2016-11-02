package com.smartdevicelink.api.vehicledata;

import com.smartdevicelink.api.interfaces.SdlContext;

import java.util.EnumSet;
import java.util.HashMap;

public class SdlVehicleDataManager {

    private HashMap<SdlDataEnums, Object> mCurrentData = new HashMap<>();
    private final SdlContext mContext;
    private EnumSet<SdlDataEnums> subscribedData = EnumSet.noneOf(SdlDataEnums.class);

    SdlVehicleDataManager(SdlContext context){
        mContext = context;
    }

    public Object getVehicleData(SdlDataEnums dataEnum){
        //check permission manager
        return mCurrentData.get(dataEnum);
    }

    public Object subscribeVehicleData(SdlDataEnums dataEnum, SdlVehicleDataListener listener){
        return null;
    }

    public Object unsubscribeVehicleData(SdlDataEnums dataEnum, SdlVehicleDataListener listener){
        return null;
    }

    public boolean pullVehicleData(SdlDataEnums dataEnum){
        return false;
    }
    

}
