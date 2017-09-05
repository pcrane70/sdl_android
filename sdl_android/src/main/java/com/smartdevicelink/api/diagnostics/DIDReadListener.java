package com.smartdevicelink.api.diagnostics;

public interface DIDReadListener {

    void onReadComplete(DID did);

    void onTimeout(DID did);

    void onCanceled(DID did);

}
