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


    public boolean subscribeToMediaButton(final ButtonName name, SdlContext context){
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
            context.sendRpc(newButtonSub);
            return true;
        }else
            return false;
    }

    public boolean unsubScribeToMediaButton(final ButtonName name, SdlContext context){
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
            return true;
        }else
            return false;
    }
}
