package com.smartdevicelink.api.vehicledata;

public interface GetVehicleDataListener {

    void onReadComplete(Object data);

    void onTimeout();

    void onCanceled();

}
