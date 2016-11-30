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

    @Override
    public DID clone(){
        DID did = new DID();
        did.setAddress(mAddress);

        if(mResults != null) {
            SparseArray<DIDLocation> newResults = new SparseArray<>();
            for (int i = 0; i < mResults.size(); i++){
                newResults.put(mResults.keyAt(i), mResults.valueAt(i).clone());
            }
        }

        return did;
    }
}
