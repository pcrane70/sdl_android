package com.smartdevicelink.api.vehicledata;

import android.util.Log;

import java.util.concurrent.PriorityBlockingQueue;

public class VehicleDataInvoker {
    private static final String TAG = VehicleDataInvoker.class.getSimpleName();
    private static final String VEHICLE_DATA_THREAD_NAME = "sdl_vehicle_data_command_thread";

    private static int SUBMISSION_COUNT = 0;
    private static final Object COUNT_LOCK = new Object();
    private final PriorityBlockingQueue<VehicleDataCommand> mCommandQueue = new PriorityBlockingQueue<>();
    private final Thread mInvokerThread;
    private volatile boolean isStopping = false;
    private boolean commandTimeout = true;

    public VehicleDataInvoker(){
        mInvokerThread = new Thread(mExecutionLoop, VEHICLE_DATA_THREAD_NAME);
        mInvokerThread.start();
    }

    public int submitCommand(VehicleDataCommand command){
        int submissionId = getSubmissionId();
        command.setCommandId(getSubmissionId());
        mCommandQueue.add(command);
        return submissionId;
    }

    public void stop(){
        isStopping = true;
        synchronized (mInvokerThread){
            mInvokerThread.interrupt();;
        }
    }


    private Runnable mExecutionLoop = new Runnable() {
        @Override
        public void run() {
            while (!isStopping){
                VehicleDataCommand executionCommand;
                try {
                    executionCommand = mCommandQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }

                if(!isStopping && executionCommand != null){
                    synchronized (mInvokerThread){
                        executionCommand.execute(new VehicleDataCommand.CompletionListener() {
                            @Override
                            public void onComplete() {
                                synchronized (mInvokerThread){
                                    commandTimeout = false;
                                    mInvokerThread.notify();
                                }
                            }
                        });

                        try {
                            mInvokerThread.wait(executionCommand.getTimeout());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    synchronized (mInvokerThread){
                        if(commandTimeout){
                            executionCommand.onTimeout();
                        } else {
                            commandTimeout = true;
                        }
                    }

                    if(!executionCommand.isFinished()){
                        mCommandQueue.put(executionCommand);
                    }


                } else if(executionCommand == null){
                    Log.wtf(TAG, "The execution command was found to be null");
                }
            }
            mCommandQueue.clear();
        }
    };

    private int getSubmissionId(){
        synchronized (COUNT_LOCK){
            return SUBMISSION_COUNT++;
        }
    }
}
