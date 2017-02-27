package com.smartdevicelink.api.vehicledata;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.GetVehicleDataResponse;
import com.smartdevicelink.proxy.rpc.OnVehicleData;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class SdlVehicleDataManager {
    private static final String TAG = SdlVehicleDataManager.class.getSimpleName();

    private HashMap<SdlDataEnums, Object> mCurrentData = new HashMap<>();
    private HashMap<SdlDataEnums,CopyOnWriteArrayList<SdlVehicleDataListener>> mVehDataListenerMap = new HashMap<>();
    private final Object DATA_LOCK = new Object();
    private final SdlVehicleDataPermissions mPermissions;

    public SdlVehicleDataManager(SdlContext context){
        mPermissions = new SdlVehicleDataPermissions( context, context.getSdlPermissionManager());
    }

    public @Nullable Object getVehicleData(SdlDataEnums dataEnum){
        synchronized (DATA_LOCK){
            return mCurrentData.get(dataEnum);
        }
    }

    public void subscribeVehicleData(@NonNull final SdlDataEnums dataEnum, @NonNull SdlVehicleDataListener listener){
        CopyOnWriteArrayList<SdlVehicleDataListener> dataListeners = mVehDataListenerMap.get(dataEnum);
        if(dataEnum == SdlDataEnums.VIN){
            throw new RuntimeException("VIN is not supported for Vehicle data subscription");
        }
        if(dataListeners == null){
            dataListeners = new CopyOnWriteArrayList<>();
            mVehDataListenerMap.put(dataEnum,dataListeners);
        }
        if(dataListeners.isEmpty()){
            mPermissions.subscribeVehicleData(dataEnum);
        }
        dataListeners.add(listener);
    }

    public void unsubscribeVehicleData(@NonNull SdlDataEnums dataEnum, @NonNull SdlVehicleDataListener listener){
        CopyOnWriteArrayList<SdlVehicleDataListener> dataListeners = mVehDataListenerMap.get(dataEnum);
        if(dataEnum == SdlDataEnums.VIN){
            throw new RuntimeException("VIN is not supported for Vehicle data unsubscription");
        }
        if(dataListeners==null || dataListeners.isEmpty()){
            Log.w(TAG,"Unable to unsubscribe because there were no listeners subscribed to the data.");
            return;
        }
        boolean listenerRemoved = dataListeners.remove(listener);
        if(dataListeners.isEmpty() && listenerRemoved){
            mPermissions.unsubscribeVehicleData(dataEnum);
        }
    }

    public boolean pullVehicleData(@NonNull SdlDataEnums dataEnum){
        return mPermissions.sendVehiclePull(dataEnum);
    }

    public void OnGetVehicleData(GetVehicleDataResponse data){
        updateData(data);
    }

    public void OnVehicleData(OnVehicleData data){
        for(SdlDataEnums enums: updateData(data)){
            if(mVehDataListenerMap.containsKey(enums)){
                for(SdlVehicleDataListener listener:mVehDataListenerMap.get(enums)){
                    listener.onDataChanged(getVehicleData(enums));
                }
            }
        }
    }

    private EnumSet<SdlDataEnums> updateData(RPCMessage data){
        synchronized (DATA_LOCK){
            EnumSet<SdlDataEnums> changeSet = EnumSet.noneOf(SdlDataEnums.class);
            for(SdlDataEnums enums: SdlDataEnums.values()){
                Object potentialData = data.getParameters(enums.getKeyName());
                if(potentialData!=null){
                    mCurrentData.put(enums, potentialData);
                    changeSet.add(enums);
                }
            }
            return changeSet;
        }
    }

}
