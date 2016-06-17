package com.smartdevicelink.api.properties;

import android.support.annotation.Nullable;

import com.smartdevicelink.api.SdlApplication;
import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.proxy.RPCResponse;
import com.smartdevicelink.proxy.rpc.KeyboardProperties;
import com.smartdevicelink.proxy.rpc.SetGlobalProperties;
import com.smartdevicelink.proxy.rpc.enums.ButtonName;
import com.smartdevicelink.proxy.rpc.enums.Result;
import com.smartdevicelink.proxy.rpc.listeners.OnRPCResponseListener;

/**
 * Created by mschwerz on 6/16/16.
 */
public class SdlGlobalProperties {
    final SdlVRProperties mVRProperties;
    final SdlMenuProperties mMenuProperties;
    final KeyboardProperties mKeyboardProperties;

    SdlGlobalProperties(Builder builder){
        mVRProperties= builder.mVRProperties;
        mMenuProperties= builder.mMenuProperties;
        mKeyboardProperties= builder.mKeyboardProperties;
    }

    public boolean update(SdlApplication application){
        SetGlobalProperties newProperties= generateGlobalProperties();
        newProperties.setOnRPCResponseListener(new OnRPCResponseListener() {
            @Override
            public void onResponse(int correlationId, RPCResponse response) {

            }

            @Override
            public void onError(int correlationId, Result resultCode, String info) {
                super.onError(correlationId, resultCode, info);
            }
        });
        application.sendRpc(newProperties);
        return false;
    }

    private SetGlobalProperties generateGlobalProperties(){
        SetGlobalProperties globalProperties= new SetGlobalProperties();
        if(mVRProperties!=null)
            mVRProperties.decorateProperties(globalProperties);
        if(mMenuProperties!=null)
            mMenuProperties.decorateProperties(globalProperties);
        if(mKeyboardProperties!=null)
            globalProperties.setKeyboardProperties(mKeyboardProperties);

        return globalProperties;
    }

    public static class Builder{
        SdlVRProperties mVRProperties;
        SdlMenuProperties mMenuProperties;
        KeyboardProperties mKeyboardProperties;

        public Builder(){

        }

        public Builder setVRProperties(SdlVRProperties vrProperties){
            mVRProperties= vrProperties;
            return this;
        }

        public Builder setMenuProperties(SdlMenuProperties menuProperties){
            mMenuProperties= menuProperties;
            return this;
        }

        public Builder setKeyboardProperties(KeyboardProperties keyboardProperties){
            mKeyboardProperties= keyboardProperties;
            return this;
        }

        public SdlGlobalProperties build(){
            return new SdlGlobalProperties(this);
        }
    }
}
