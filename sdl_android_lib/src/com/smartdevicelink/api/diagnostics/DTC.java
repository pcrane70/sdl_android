package com.smartdevicelink.api.diagnostics;

import com.smartdevicelink.proxy.rpc.enums.Result;

import java.util.List;

public class DTC {
    private int mAddress;
    private Result mResultCode;
    private List<String> mCodes;
    private Integer mEcuHeader;

    public int getAddress() {
        return mAddress;
    }

    public void setAddress(int address) {
        mAddress = address;
    }

    public Result getResultCode() {
        return mResultCode;
    }

    public void setResultCode(Result resultCode) {
        mResultCode = resultCode;
    }

    public List<String> getCodes() {
        return mCodes;
    }

    public void setCodes(List<String> codes) {
        mCodes = codes;
    }

    public Integer getEcuHeader() {
        return mEcuHeader;
    }

    public void setEcuHeader(Integer ecuHeader) {
        mEcuHeader = ecuHeader;
    }
}
