package com.smartdevicelink.api.diagnostics;

import android.util.SparseArray;

import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.proxy.RPCResponse;
import com.smartdevicelink.proxy.rpc.DIDResult;
import com.smartdevicelink.proxy.rpc.ReadDID;
import com.smartdevicelink.proxy.rpc.ReadDIDResponse;
import com.smartdevicelink.proxy.rpc.enums.VehicleDataResultCode;
import com.smartdevicelink.proxy.rpc.listeners.OnRPCResponseListener;

import java.util.List;

public class ReadDidCommand extends DiagnosticCommand {

    private DID mDidToRead;
    private DIDReadListener mReadListener;
    private List<Integer> mLocations;

    public ReadDidCommand(SdlContext sdlContext, int timeout, int priority, DID didToRead,
                          List<Integer> locations, DIDReadListener listener) {
        super(sdlContext, timeout, priority);
        mDidToRead = new DID();
        mDidToRead.setAddress(didToRead.getAddress());
        mReadListener = listener;
        mLocations = locations;
    }

    @Override
    public void execute(final CompletionCallback callback) {
        ReadDID rpc = didToRpc(mDidToRead, mLocations);
        rpc.setOnRPCResponseListener(new OnRPCResponseListener() {
            @Override
            public void onResponse(int correlationId, RPCResponse response) {
                ReadDIDResponse didResponse = (ReadDIDResponse) response;
                SparseArray<DIDLocation> results = new SparseArray<>();
                if(response.getSuccess() && didResponse.getDidResult() != null){
                    for (DIDResult didResult : didResponse.getDidResult()) {
                        DIDLocation location = new DIDLocation(didResult.getDidLocation());
                        location.setData(didResult.getData());
                        location.setResultCode(didResult.getResultCode());

                        results.append(location.getAddress(), location);
                    }
                } else {
                    for(Integer address: mLocations){
                        DIDLocation location = new DIDLocation(address);
                        location.setResultCode(VehicleDataResultCode.VEHICLE_DATA_NOT_AVAILABLE);

                        results.append(location.getAddress(), location);
                    }
                }
                mDidToRead.setResults(results);
                mReadListener.onReadComplete(mDidToRead);
                callback.onComplete();
            }
        });
        mSdlContext.sendRpc(rpc);
    }

    @Override
    public void onTimeout() {
        mReadListener.onTimeout();
    }

    @Override
    public void cancel() {
        mReadListener.onCanceled();
    }

    private ReadDID didToRpc(DID did, List<Integer> locations){
        ReadDID rpc = new ReadDID();
        rpc.setEcuName(did.getAddress());
        rpc.setDidLocation(locations);
        return rpc;
    }

}
