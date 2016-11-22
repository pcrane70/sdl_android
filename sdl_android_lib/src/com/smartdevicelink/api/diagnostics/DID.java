package com.smartdevicelink.api.diagnostics;

import android.util.SparseArray;

public class DID {
    private int mAddress;
    private SparseArray<DIDLocation> mResults;

    public int getAddress() {
        return mAddress;
    }

    public void setAddress(int address) {
        mAddress = address;
    }

    public SparseArray<DIDLocation> getResults() {
        return mResults;
    }

    public void setResults(SparseArray<DIDLocation> results) {
        mResults = results;
    }
}
