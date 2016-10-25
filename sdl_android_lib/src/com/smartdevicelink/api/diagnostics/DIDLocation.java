package com.smartdevicelink.api.diagnostics;

import com.smartdevicelink.proxy.rpc.VehicleDataResult;

public class DIDLocation {
    private int mAddress;
    private VehicleDataResult mResultCode;
    private String mData;

    public DIDLocation(int address){
        mAddress = address;
    }

    public int getAddress() {
        return mAddress;
    }

    public void setAddress(int address) {
        mAddress = address;
    }

    public VehicleDataResult getResultCode() {
        return mResultCode;
    }

    public void setResultCode(VehicleDataResult resultCode) {
        mResultCode = resultCode;
    }

    public String getData() {
        return mData;
    }

    public void setData(String data) {
        mData = data;
    }
}
