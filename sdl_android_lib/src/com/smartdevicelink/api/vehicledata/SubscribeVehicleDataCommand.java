package com.smartdevicelink.api.vehicledata;

import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.proxy.RPCRequest;
import com.smartdevicelink.proxy.RPCResponse;
import com.smartdevicelink.proxy.rpc.SubscribeVehicleData;
import com.smartdevicelink.proxy.rpc.UnsubscribeVehicleData;
import com.smartdevicelink.proxy.rpc.listeners.OnRPCResponseListener;

import java.util.EnumSet;

public class SubscribeVehicleDataCommand extends VehicleDataCommand {

    private final boolean isSubscribing;
    private final EnumSet<SdlDataEnums> mSubEnums;
    private SubscribeStatusListener mListener;

    SubscribeVehicleDataCommand(int timeout, int priority, SdlContext sdlContext,
                                boolean subscribing, EnumSet<SdlDataEnums> vehDataEnums){
        super(timeout, priority, sdlContext);
        isSubscribing = subscribing;
        mSubEnums = vehDataEnums;
    }

    public void setListener(SubscribeStatusListener listener){
        mListener = listener;
    }


    @Override
    public void execute(final CompletionListener listener) {
        final RPCRequest rpc;
        if(isSubscribing){
            rpc = new SubscribeVehicleData();
        } else {
            rpc = new UnsubscribeVehicleData();
        }
        for(SdlDataEnums subEnum : mSubEnums){
            rpc.setParameters(subEnum.getKeyName(), true);
        }
        rpc.setOnRPCResponseListener(new OnRPCResponseListener() {
            @Override
            public void onResponse(int correlationId, RPCResponse response) {
                if(mListener != null)
                    mListener.onCompleted(mSubEnums);
                listener.onComplete();
            }
        });
        mSdlHandler.post(new Runnable() {
            @Override
            public void run() {
                mSdlContext.sendRpc(rpc);
            }
        });
    }

    @Override
    public void stop() {
        if(mListener != null)
            mListener.onCanceled(mSubEnums);
    }

    @Override
    public void onTimeout() {
        if(mListener != null)
            mListener.onTimeout(mSubEnums);
    }
}
