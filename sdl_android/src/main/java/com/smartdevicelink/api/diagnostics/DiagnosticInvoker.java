package com.smartdevicelink.api.diagnostics;

import android.util.Log;
import android.util.SparseArray;

import java.util.concurrent.PriorityBlockingQueue;

public class DiagnosticInvoker {

    private static final String TAG = DiagnosticInvoker.class.getSimpleName();

    private static final String DIAGNOSTIC_THREAD_NAME = "sdl_diagnostic_command_thread";

    private static int SUBMISSION_COUNT = 0;
    private static final Object COUNT_LOCK = new Object();
    private PriorityBlockingQueue<DiagnosticCommand> mDiagnosticQueue = new PriorityBlockingQueue<>();
    private SparseArray<DiagnosticCommand> mCommandRegistry = new SparseArray<>();
    private final Thread mExecutionThread;
    private volatile boolean isStopping = false;
    private boolean didCommandTimeout = true;

    public DiagnosticInvoker(){
        mExecutionThread = new Thread(mExecutionRunnable, DIAGNOSTIC_THREAD_NAME);
        mExecutionThread.start();
    }

    public int submitCommand(DiagnosticCommand command){
        int submissionId = getSubmissionId();
        command.setCommandId(getSubmissionId());
        mCommandRegistry.put(submissionId, command);
        mDiagnosticQueue.add(command);
        return submissionId;
    }

    public void stop(){

        Log.i(TAG, "I'm Stopping.");
        isStopping = true;
        synchronized (mExecutionThread) {
            mExecutionThread.interrupt();
        }
    }

    private Runnable mExecutionRunnable = new Runnable() {
        @Override
        public void run() {
            while (!isStopping){
                DiagnosticCommand command;
                try {
                    command = mDiagnosticQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }

                if(!isStopping && command != null) {
                    synchronized (mExecutionThread) {
                        command.execute(new DiagnosticCommand.CompletionCallback() {
                            @Override
                            public void onComplete() {
                                synchronized (mExecutionThread) {
                                    didCommandTimeout = false;

                                    mExecutionThread.notify();
                                }
                            }
                        });

                        try {
                            mExecutionThread.wait(command.getTimeout());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    synchronized (mExecutionThread){
                        if(didCommandTimeout){
                            command.onTimeout();
                        } else {
                            didCommandTimeout = true;
                        }
                    }

                    /* Remove the command from the registry if it is finished, replace it in the queue if not.
                     * This must be done to use the blocking operation PriorityBlockingQueue#take()
                     * instead of the non-blocking operation PriorityBlockingQueue#peek();
                     */
                    if(command.isFinished()){
                        mCommandRegistry.remove(command.getCommandId());
                    } else {
                        mDiagnosticQueue.put(command);
                    }
                }
            }
            Log.i(TAG, "I'm exiting mah runnable and clearin my stuff.");
            mCommandRegistry.clear();
            mDiagnosticQueue.clear();
        }
    };

    private int getSubmissionId(){
        synchronized (COUNT_LOCK){
            return SUBMISSION_COUNT++;
        }
    }

}
