package com.smartdevicelink.api.diagnostics;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;

import com.smartdevicelink.api.SdlApplication;
import com.smartdevicelink.api.interfaces.SdlContext;

import java.util.HashMap;
import java.util.List;

public class DiagnosticManager implements SdlApplication.LifecycleListener{

    public static final int DEFAULT_TIMEOUT = 5 * 1000;

    public static final int PRIORITY_MIN = 0;
    public static final int PRIORITY_LOW = 1;
    public static final int PRIORITY_DEFAULT = 2;
    public static final int PRIORITY_HIGH = 3;
    public static final int PRIORITY_MAX = 4;

    private final SdlContext mSdlContext;
    private DiagnosticInvoker mDiagnosticInvoker;

    public DiagnosticManager(SdlContext sdlContext){
        mSdlContext = sdlContext;
    }

    /**
     * Queues a DID read request with default timeout and priority.
     * @param address Address of the ECU on the CAN.
     * @param locations List of location on the given ECU to be read.
     * @param listener Listener that is called upon completion of the read.
     * @return Id of the resulting command submitted to the diagnostic queue. Will be -1 on failure to queue request.
     */
    public int readDID(int address, List<Integer> locations, DIDReadListener listener){
        return readDID(address, locations, DEFAULT_TIMEOUT, PRIORITY_DEFAULT, listener);
    }

    /**
     * Queues a DID read request.
     * @param address Address of the ECU on the CAN.
     * @param locations List of location on the given ECU to be read.
     * @param timeout Amount of time to wait for a response from the module before timing out in ms.
     * @param priority Priority of the request relative to other diagnostic requests that have been made.
     * @param listener Listener that is called upon completion of the read.
     * @return Id of the resulting command submitted to the diagnostic queue. Will be -1 on failure to queue request.
     */
    public int readDID(int address, List<Integer> locations, int timeout, int priority, DIDReadListener listener){
        DID did = new DID();
        did.setAddress(address);
        ReadDidCommand command = new ReadDidCommand(mSdlContext, timeout, priority, did, locations);
        command.setListener(listener);
        return (mDiagnosticInvoker != null ? mDiagnosticInvoker.submitCommand(command): -1);
    }

    /**
     * Queues a batch of DID read requests with default timeout and priority.
     * @param locations 2d collection where the key of the outer SparseArray indicates the ECU
     *                  address and the inner list indicates the locations to read on that ECU.
     * @param listener Listener that is called upon completion of the batch.
     * @return Id of the resulting command submitted to the diagnostic queue. Will be -1 on failure to queue request.
     */
    public int readDIDBatch(SparseArray<List<Integer>> locations, DIDBatchListener listener){
        return readDIDBatch(locations, DEFAULT_TIMEOUT, PRIORITY_DEFAULT, listener);
    }

    /**
     * Queues a batch of DID read requests.
     * @param locations 2d collection where the key of the outer SparseArray indicates the ECU
     *                  address to read from and the inner list indicates the locations to read on
     *                  that ECU.
     * @param timeout Amount of time to wait for a responses from the module before timing out on
     *                each read in the batch in ms.
     * @param priority Priority of the requests relative to other diagnostic requests that have been made.
     * @param listener Listener that is called upon completion of the batch.
     * @return Id of the resulting command submitted to the diagnostic queue. Will be -1 on failure to queue request.
     */
    public int readDIDBatch(SparseArray<List<Integer>> locations, int timeout, int priority, DIDBatchListener listener){
        DIDBatchCommand batchCommand = new DIDBatchCommand(mSdlContext, timeout, priority, listener);
        for(int i = 0; i < locations.size(); i++){
            DID did = new DID();
            did.setAddress(locations.keyAt(i));
            SparseArray<DIDLocation> didLocations = new SparseArray<>();
            for(Integer integer: locations.valueAt(i)){
                if(integer != null) {
                    DIDLocation dl = new DIDLocation(integer);
                    didLocations.append(integer, dl);
                }
            }
            did.setResults(didLocations);
            ReadDidCommand didCommand = new ReadDidCommand(mSdlContext, timeout, priority, did, locations.valueAt(i));
            batchCommand.addCommand(didCommand);
        }
        return (mDiagnosticInvoker != null ? mDiagnosticInvoker.submitCommand(batchCommand): -1);
    }

    /**
     * Queues a DTC read request with default timeout and priority.
     * @param address Address of the ECU on the CAN to read DTCs from.
     * @param mask Mask to apply to DTC request.
     * @param listener Listener that is called upon completion of the read.
     * @return Id of the resulting command submitted to the diagnostic queue. Will be -1 on failure to queue request.
     */
    public int readDTC(int address, @Nullable Integer mask, @NonNull DTCReadListener listener){
        return readDTC(address, mask, DEFAULT_TIMEOUT, PRIORITY_DEFAULT, listener);
    }

    /**
     * Queues a DTC read request.
     * @param address Address of the ECU on the CAN to read DTCs from.
     * @param mask Mask to apply to DTC request.
     * @param timeout Amount of time to wait for a response from the module before timing out in ms.
     * @param priority Priority of the request relative to other diagnostic requests that have been made.
     * @param listener Listener that is called upon completion of the read.
     * @return Id of the resulting command submitted to the diagnostic queue. Will be -1 on failure to queue request.
     */
    public int readDTC(int address, @Nullable Integer mask, int timeout, int priority, DTCReadListener listener){
        ReadDtcCommand dtcCommand = new ReadDtcCommand(mSdlContext, timeout, priority, address, mask, listener);
        return (mDiagnosticInvoker != null ? mDiagnosticInvoker.submitCommand(dtcCommand): -1);
    }

    /**
     * Queues a batch of DTC read requests with default timeout and priority.
     * @param addresses {@link HashMap} where the key represents the address to be read from and the
     *                                 value represents the mask to apply to the DTC request. A null
     *                                 mask will result in an unmasked read for the given key.
     * @param listener Listener that is called upon completion of the batch.
     * @return Id of the resulting command submitted to the diagnostic queue. Will be -1 on failure to queue request.
     */
    public int readDTCBatch(HashMap<Integer, Integer> addresses, DIDBatchListener listener){
        return readDTCBatch(addresses, DEFAULT_TIMEOUT, PRIORITY_DEFAULT, listener);
    }

    /**
     * Queues a batch of DTC read requests.
     * @param addresses {@link HashMap} where the key represents the address to be read from and the
     *                                 value represents the mask to apply to the DTC request. A null
     *                                 mask will result in an unmasked read for the given key.
     * @param timeout Amount of time to wait for a responses from the module before timing out on
     *                each read in the batch in ms.
     * @param priority Priority of the requests relative to other diagnostic requests that have been made.
     * @param listener Listener that is called upon completion of the batch.
     * @return Id of the resulting command submitted to the diagnostic queue. Will be -1 on failure to queue request.
     */
    public int readDTCBatch(HashMap<Integer, Integer> addresses, int timeout, int priority, DIDBatchListener listener){
        throw new RuntimeException(this.getClass().getCanonicalName() + "#readDTCBatch() not yet implemented.");
    }

    @Override
    public void onSdlConnect() {
        mDiagnosticInvoker = new DiagnosticInvoker();
    }

    @Override
    public void onBackground() {
        // TODO (tstrayer, 12/4/17): Implement method stub
    }

    @Override
    public void onForeground() {
        // TODO (tstrayer, 12/4/17): Implement method stub
    }

    @Override
    public void onExit() {
        // TODO (tstrayer, 12/4/17): Implement method stub
    }

    @Override
    public void onSdlDisconnect() {
        mDiagnosticInvoker.stop();
        mDiagnosticInvoker = null;
    }
}
