package com.smartdevicelink.api.vehicledata;

import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.proxy.RPCResponse;
import com.smartdevicelink.proxy.rpc.GetVehicleData;
import com.smartdevicelink.proxy.rpc.listeners.OnRPCResponseListener;

public class GetVehicleDataCommand extends VehicleDataCommand {

    private final SdlDataEnums mGetDataEnum;
    private VehicleDataCallback mGetVehReadListener;

    GetVehicleDataCommand(int timeout, int priority,
                          SdlContext sdlContext, SdlDataEnums vehDataEnum){
        super(timeout, priority, sdlContext);
        mGetDataEnum = vehDataEnum;
    }

    void setReadListener(VehicleDataCallback listener){
        mGetVehReadListener = listener;
    }


    @Override
    public void execute(final CompletionListener listener) {
        final GetVehicleData getVehDataRPC = new GetVehicleData();
        getVehDataRPC.setParameters(mGetDataEnum.getVehicleDataRequestKeyName(), true);
        getVehDataRPC.setOnRPCResponseListener(new OnRPCResponseListener() {
            @Override
            public void onResponse(int correlationId, RPCResponse response) {
                if(mGetVehReadListener != null){
                    mGetVehReadListener.onReadComplete(response
                            .getParameters(mGetDataEnum.getVehicleDataResponseKeyName()));
                }
                listener.onComplete();
            }
        });
        mSdlHandler.post(new Runnable() {
            @Override
            public void run() {
                mSdlContext.sendRpc(getVehDataRPC);
            }
        });
    }

    @Override
    public void stop() {
        if(mGetVehReadListener != null)
            mGetVehReadListener.onCanceled();
    }

    @Override
    public void onTimeout() {
        if(mGetVehReadListener != null)
            mGetVehReadListener.onTimeout();
    }
}
