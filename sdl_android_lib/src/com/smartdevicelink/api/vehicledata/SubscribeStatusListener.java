package com.smartdevicelink.api.vehicledata;

import java.util.EnumSet;

public interface SubscribeStatusListener {
    void onCompleted(EnumSet<SdlDataEnums> subEnums);
    void onTimeout(EnumSet<SdlDataEnums> subEnums);
    void onCanceled(EnumSet<SdlDataEnums> subEnums);
}
