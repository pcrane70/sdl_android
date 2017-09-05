package com.smartdevicelink.api.diagnostics;

import java.util.List;

public interface DTCBatchListener {

    void onBatchComplete(List<DTC> dtcs);

    void onTimeout();

    void onCanceled();

}
