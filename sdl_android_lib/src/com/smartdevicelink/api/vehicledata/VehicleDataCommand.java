package com.smartdevicelink.api.vehicledata;

import android.os.Handler;

import com.smartdevicelink.api.interfaces.SdlContext;

public abstract class VehicleDataCommand implements Comparable<VehicleDataCommand> {

    private final int mTimeout;
    private final int mPriority;
    private int mCommandId;
    protected SdlContext mSdlContext;
    protected Handler mSdlHandler;

    VehicleDataCommand(int timeout, int priority, SdlContext sdlContext){
        mTimeout = timeout;
        mPriority = priority;
        mSdlContext = sdlContext;
        mSdlHandler = new Handler(sdlContext.getSdlExecutionLooper());

    }


    public abstract void execute(CompletionListener listener);

    public abstract void stop();


    public int getTimeout(){
        return mTimeout;
    }

    public int getPriority() {return mPriority;}

    public abstract void onTimeout();

    public boolean isFinished(){
        return true;
    }
    public void setCommandId(int commandId){
        mCommandId = commandId;
    }

    public int getCommandId(){
        return mCommandId;
    }

    @Override
    public int compareTo(VehicleDataCommand another) {
        int difference = mPriority - another.getPriority();
        if(difference == 0){
            difference = mCommandId - another.getCommandId();
        }
        return Integer.signum(difference);
    }

    interface CompletionListener{
        void onComplete();
    }
}
