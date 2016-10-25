package com.smartdevicelink.api.diagnostics;

import com.smartdevicelink.proxy.rpc.enums.VehicleDataResultCode;

import java.util.ArrayList;

public class DTC {
    private int mAddress;
    private VehicleDataResultCode mResultCode;
    private ArrayList<String> mCodes;
    private Integer mEcuHeader;

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

    public ArrayList<String> getCodes() {
        return mCodes;
    }

    public void setCodes(ArrayList<String> codes) {
        mCodes = codes;
    }

    public Integer getEcuHeader() {
        return mEcuHeader;
    }

    public void setEcuHeader(Integer ecuHeader) {
        mEcuHeader = ecuHeader;
    }
}
