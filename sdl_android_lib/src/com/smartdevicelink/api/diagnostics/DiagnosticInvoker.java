package com.smartdevicelink.api.diagnostics;

import java.util.concurrent.PriorityBlockingQueue;

public class DiagnosticInvoker {

    private PriorityBlockingQueue<DiagnosticCommand> mDiagnosticQueue = new PriorityBlockingQueue<>();

    private final Thread mExecutionThread;

    public DiagnosticInvoker(){
        mExecutionThread = new Thread(mExecutionRunnable);
    }

    private Runnable mExecutionRunnable = new Runnable() {
        @Override
        public void run() {

        }
    };

}
