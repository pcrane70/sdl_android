package com.smartdevicelink.api.vehicledata;

import android.support.annotation.Nullable;
import android.util.Log;

import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.api.permission.SdlPermission;
import com.smartdevicelink.proxy.RPCMessage;
import com.smartdevicelink.proxy.rpc.GetVehicleData;
import com.smartdevicelink.proxy.rpc.GetVehicleDataResponse;
import com.smartdevicelink.proxy.rpc.OnVehicleData;
import com.smartdevicelink.proxy.rpc.SubscribeVehicleData;
import com.smartdevicelink.proxy.rpc.UnsubscribeVehicleData;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class SdlVehicleDataManager {
    private static final String TAG = SdlVehicleDataManager.class.getSimpleName();

    private HashMap<SdlDataEnums, Object> mCurrentData = new HashMap<>();
    private final SdlContext mContext;
    private HashMap<SdlDataEnums,CopyOnWriteArrayList<SdlVehicleDataListener>> mVehDataListenerMap = new HashMap<>();
    private final Object DATA_LOCK = new Object();

    public SdlVehicleDataManager(SdlContext context){
        mContext = context;
    }

    public @Nullable Object getVehicleData(SdlDataEnums dataEnum){
        synchronized (DATA_LOCK){
            return mCurrentData.get(dataEnum);
        }
    }

    public boolean subscribeVehicleData(final SdlDataEnums dataEnum, SdlVehicleDataListener listener){
        CopyOnWriteArrayList<SdlVehicleDataListener> dataListeners = mVehDataListenerMap.get(dataEnum);
        if(dataEnum == SdlDataEnums.VIN){
            Log.w(TAG, "VIN is not supported for subscription");
            return false;
        }
        if(dataListeners==null){
            dataListeners = new CopyOnWriteArrayList<>();
            mVehDataListenerMap.put(dataEnum,dataListeners);
        }
        if(dataListeners.isEmpty() &&
                mContext.getSdlPermissionManager().isPermissionAvailable(generatePermission("Subscribe",dataEnum))){
            SubscribeVehicleData request = new SubscribeVehicleData();
            request.setParameters(dataEnum.getKeyName(),true);
            mContext.sendRpc(request);
            dataListeners.add(listener);
            return true;
        }else if(!dataListeners.isEmpty()){
            dataListeners.add(listener);
            return true;
        } else{
            return false;
        }
    }

    public boolean unsubscribeVehicleData(SdlDataEnums dataEnum, SdlVehicleDataListener listener){
        CopyOnWriteArrayList<SdlVehicleDataListener> dataListeners = mVehDataListenerMap.get(dataEnum);
        if(dataListeners==null || dataListeners.isEmpty()){
            Log.w(TAG,"Unable to unsubscribe because there were no listeners subscribed to the data.");
            return false;
        }
        boolean listenerRemoved = dataListeners.remove(listener);
        if(dataListeners.isEmpty() &&
                mContext.getSdlPermissionManager().isPermissionAvailable(generatePermission("Unsubscribe",dataEnum))){
                UnsubscribeVehicleData request = new UnsubscribeVehicleData();
                request.setParameters(dataEnum.getKeyName(),true);
                mContext.sendRpc(request);

        }
        return listenerRemoved;
    }

    public boolean pullVehicleData(SdlDataEnums dataEnum){
        if(mContext.getSdlPermissionManager().isPermissionAvailable(generatePermission("Get",dataEnum))){
            GetVehicleData request = new GetVehicleData();
            request.setParameters(dataEnum.getKeyName(),true);
            mContext.sendRpc(request);
            return true;
        } else {
            return false;
        }
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

    private SdlPermission generatePermission(String permissionPrefix, SdlDataEnums dataEnum ) throws IllegalArgumentException{
        char[] chars = dataEnum.getKeyName().toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        String permissionName = permissionPrefix + new String(chars);
        return SdlPermission.valueOf(permissionName);
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
