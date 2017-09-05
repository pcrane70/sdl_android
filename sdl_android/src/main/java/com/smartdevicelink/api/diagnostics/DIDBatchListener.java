package com.smartdevicelink.api.diagnostics;

import java.util.List;

public interface DIDBatchListener {

    void onBatchComplete(List<DID> dids);
}
