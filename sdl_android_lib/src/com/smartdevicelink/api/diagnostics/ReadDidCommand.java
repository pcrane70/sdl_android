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
                          List<Integer> locations) {
        super(sdlContext, timeout, priority);
        mDidToRead = new DID();
        mDidToRead.setAddress(didToRead.getAddress());
        mLocations = locations;
    }

    public void setListener(DIDReadListener listener){
        mReadListener = listener;
    }

    @Override
    public void execute(final CompletionCallback callback) {
        final ReadDID rpc = didToRpc(mDidToRead, mLocations);
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
                    markResultsUnavailable(results);
                }
                mDidToRead.setResults(results);
                mReadListener.onReadComplete(mDidToRead);
                isFinished = true;
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

    private void markResultsUnavailable(SparseArray<DIDLocation> results) {
        for(Integer address: mLocations){
            DIDLocation location = new DIDLocation(address);
            location.setResultCode(VehicleDataResultCode.VEHICLE_DATA_NOT_AVAILABLE);

            results.append(location.getAddress(), location);
        }
    }

    @Override
    public void onTimeout() {
        SparseArray<DIDLocation> results = new SparseArray<>();
        markResultsUnavailable(results);
        mDidToRead.setResults(results);
        mReadListener.onTimeout(mDidToRead);
    }

    @Override
    public void cancel() {
        SparseArray<DIDLocation> results = new SparseArray<>();
        markResultsUnavailable(results);
        mDidToRead.setResults(results);
        mReadListener.onCanceled(mDidToRead);
    }

    private ReadDID didToRpc(DID did, List<Integer> locations){
        ReadDID rpc = new ReadDID();
        rpc.setEcuName(did.getAddress());
        rpc.setDidLocation(locations);
        return rpc;
    }

}
