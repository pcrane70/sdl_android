package com.smartdevicelink.api.diagnostics;

import java.util.List;

public interface DTCBatchListener {

    void onBatchComplete(List<DTC> dids);
}
