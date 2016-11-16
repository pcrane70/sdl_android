package com.smartdevicelink.api.diagnostics;

public interface DIDReadListener {

    void onReadComplete(DID dtc);

    void onTimeout();

    void onCanceled();

}
