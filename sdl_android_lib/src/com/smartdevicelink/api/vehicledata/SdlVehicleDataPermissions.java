package com.smartdevicelink.api.vehicledata;

import android.support.annotation.NonNull;

import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.api.permission.SdlPermissionEvent;
import com.smartdevicelink.api.permission.SdlPermissionListener;
import com.smartdevicelink.api.permission.SdlPermissionManager;
import com.smartdevicelink.proxy.rpc.GetVehicleData;
import com.smartdevicelink.proxy.rpc.SubscribeVehicleData;
import com.smartdevicelink.proxy.rpc.UnsubscribeVehicleData;

import java.util.HashMap;

class SdlVehicleDataPermissions {
    private final SdlContext mContext;
    private final SdlPermissionManager mPermissionManager;
    //could be a SparseArray instead
    private HashMap<SdlDataEnums, SdlPermissionListener> mListeners = new HashMap<>();

    SdlVehicleDataPermissions(SdlContext context, SdlPermissionManager manager){
        mContext = context;
        mPermissionManager = manager;
    }


    void subscribeVehicleData(final SdlDataEnums dataSub){
        if(mPermissionManager.isPermissionAvailable(dataSub.getSubVehicleDataPermission())){
            sendSubscribeVehicleData(dataSub);
        } else {
            mListeners.put(dataSub, new SdlPermissionListener() {
                @Override
                public void onPermissionChanged(@NonNull SdlPermissionEvent event) {
                    if(event.getPermissionLevel() == SdlPermissionEvent.PermissionLevel.ALL){
                        sendSubscribeVehicleData(dataSub);
                        mListeners.remove(dataSub);
                    }
                }
            });
        }
    }

    void unsubscribeVehicleData(final SdlDataEnums dataUnsub){
        if(mListeners.containsKey(dataUnsub)){
            mPermissionManager.removeListener(mListeners.get(dataUnsub));
            mListeners.remove(dataUnsub);
        }
        sendUnsubscribeVehicleData(dataUnsub);
    }

    boolean sendVehiclePull(final SdlDataEnums dataPull){
        if(mPermissionManager.isPermissionAvailable(dataPull.getGetVehicleDataPermission())){
            sendGetVehicleData(dataPull);
            return true;
        }
        return false;
    }

    private void sendSubscribeVehicleData(SdlDataEnums dataSub){
        SubscribeVehicleData request = new SubscribeVehicleData();
        request.setParameters(dataSub.getKeyName(),true);
        mContext.sendRpc(request);
    }

    private void sendUnsubscribeVehicleData(SdlDataEnums dataUnsub){
        UnsubscribeVehicleData request = new UnsubscribeVehicleData();
        request.setParameters(dataUnsub.getKeyName(),true);
        mContext.sendRpc(request);
    }

    private void sendGetVehicleData(SdlDataEnums dataGet){
        GetVehicleData request = new GetVehicleData();
        request.setParameters(dataGet.getKeyName(),true);
        mContext.sendRpc(request);
    }


}
