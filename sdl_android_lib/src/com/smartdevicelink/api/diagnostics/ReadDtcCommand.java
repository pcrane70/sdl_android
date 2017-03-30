package com.smartdevicelink.api.diagnostics;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.proxy.RPCResponse;
import com.smartdevicelink.proxy.rpc.GetDTCs;
import com.smartdevicelink.proxy.rpc.GetDTCsResponse;
import com.smartdevicelink.proxy.rpc.listeners.OnRPCResponseListener;

public class ReadDtcCommand extends DiagnosticCommand {

    private DTCReadListener mReadListener;
    private Integer mMask;
    private int mAddress;

    public ReadDtcCommand(SdlContext sdlContext, int timeout, int priority, int address,
                          @Nullable Integer mask, @NonNull DTCReadListener listener) {
        super(sdlContext, timeout, priority);
        mReadListener = listener;
        mMask = mask;
        mAddress = address;
    }

    @Override
    public void execute(final CompletionCallback callback) {
        final GetDTCs rpc = makeRpc(mAddress, mMask);
        rpc.setOnRPCResponseListener(new OnRPCResponseListener() {
            @Override
            public void onResponse(int correlationId, RPCResponse response) {
                GetDTCsResponse dtcResponse = (GetDTCsResponse) response;
                DTC dtc = new DTC();
                dtc.setAddress(mAddress);
                if(dtcResponse != null){
                    dtc.setResultCode(dtcResponse.getResultCode());
                    dtc.setCodes(dtcResponse.getDtc());
                    dtc.setEcuHeader(dtcResponse.getEcuHeader());
                }
                mReadListener.onReadComplete(dtc);
                callback.onComplete();
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
    public void onTimeout() {
        mSdlHandler.post(new Runnable() {
            @Override
            public void run() {
                mReadListener.onTimeout();
            }
        });
    }

    @Override
    public void cancel() {
        mSdlHandler.post(new Runnable() {
            @Override
            public void run() {
                mReadListener.onCanceled();
            }
        });
    }

    private GetDTCs makeRpc(int address, @Nullable Integer mask){
        GetDTCs rpc = new GetDTCs();
        rpc.setEcuName(address);
        if(mask != null) rpc.setDtcMask(mask);
        return rpc;
    }
}
