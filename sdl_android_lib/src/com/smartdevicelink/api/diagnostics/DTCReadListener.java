package com.smartdevicelink.api.diagnostics;

public interface DTCReadListener {

    void onReadComplete(DTC dtc);

    void onTimeout();

    void onCanceled();

}
