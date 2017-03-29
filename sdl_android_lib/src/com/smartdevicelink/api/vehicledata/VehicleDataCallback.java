package com.smartdevicelink.api.vehicledata;

public interface VehicleDataCallback {

    void onReadComplete(Object data);

    void onTimeout();

    void onCanceled();

}
