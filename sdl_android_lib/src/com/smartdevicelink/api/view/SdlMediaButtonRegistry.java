package com.smartdevicelink.api.view;

import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.proxy.RPCResponse;
import com.smartdevicelink.proxy.rpc.SubscribeButton;
import com.smartdevicelink.proxy.rpc.UnsubscribeButton;
import com.smartdevicelink.proxy.rpc.enums.ButtonName;
import com.smartdevicelink.proxy.rpc.enums.Result;
import com.smartdevicelink.proxy.rpc.listeners.OnRPCResponseListener;

import java.util.HashSet;

/**
 * Created by mschwerz on 6/10/16.
 */
public class SdlMediaButtonRegistry {
    HashSet<ButtonName> mButtonsSubscribed= new HashSet<>();
    SdlContext mContext;

    public SdlMediaButtonRegistry(SdlContext context){
        mContext= context;
    }


    public boolean subscribeToMediaButton(final ButtonName name){
        if(!mButtonsSubscribed.contains(name)){
            SubscribeButton newButtonSub= new SubscribeButton();
            newButtonSub.setButtonName(name);
            newButtonSub.setOnRPCResponseListener(new OnRPCResponseListener() {
                @Override
                public void onResponse(int correlationId, RPCResponse response) {
                    mButtonsSubscribed.add(name);
                }

                @Override
                public void onError(int correlationId, Result resultCode, String info) {
                    super.onError(correlationId, resultCode, info);
                }
            });
            mContext.sendRpc(newButtonSub);
            return true;
        }else
            return false;
    }

    public boolean unsubscribeToMediaButton(final ButtonName name){
        if(mButtonsSubscribed.contains(name)){
            UnsubscribeButton newButtonUnSub= new UnsubscribeButton();
            newButtonUnSub.setButtonName(name);
            newButtonUnSub.setOnRPCResponseListener(new OnRPCResponseListener() {
                @Override
                public void onResponse(int correlationId, RPCResponse response) {
                    mButtonsSubscribed.remove(name);
                }
                @Override
                public void onError(int correlationId, Result resultCode, String info) {
                    super.onError(correlationId, resultCode, info);
                }
            });
            mContext.sendRpc(newButtonUnSub);
            return true;
        }else
            return false;
    }

    public boolean clearAllSubscriptions(){
        for(ButtonName button:mButtonsSubscribed){
            if(unsubscribeToMediaButton(button)){
                return false;
            }
        }
        return true;
    }
}
