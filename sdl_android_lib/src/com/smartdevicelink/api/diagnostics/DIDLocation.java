package com.smartdevicelink.api.diagnostics;

import com.smartdevicelink.proxy.rpc.enums.VehicleDataResultCode;

public class DIDLocation {
    private int mAddress;
    private VehicleDataResultCode mResultCode;
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

    public VehicleDataResultCode getResultCode() {
        return mResultCode;
    }

    public void setResultCode(VehicleDataResultCode resultCode) {
        mResultCode = resultCode;
    }

    public String getData() {
        return mData;
    }

    public void setData(String data) {
        mData = data;
    }
}
