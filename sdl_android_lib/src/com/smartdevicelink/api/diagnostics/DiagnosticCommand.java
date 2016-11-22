package com.smartdevicelink.api.diagnostics;

import com.smartdevicelink.api.interfaces.SdlContext;

public abstract class DiagnosticCommand implements Comparable<DiagnosticCommand>{

    protected SdlContext mSdlContext;
    protected boolean isFinished;

    private boolean isBlocking;
    private int mTimeout;
    private int mPriority;
    private int mCommandId;

    public DiagnosticCommand(SdlContext sdlContext, int timeout, int priority){
        mTimeout = timeout;
        mPriority = priority;
        mSdlContext = sdlContext;
    }

    public int getTimeout() {
        return mTimeout;
    }

    public int getPriority() {
        return mPriority;
    }

    public final int getCommandId() {
        return mCommandId;
    }

    public void setCommandId(int commandId){
        mCommandId = commandId;
    }

    public boolean isFinished(){
        return isFinished;
    }

    abstract public void execute(CompletionCallback callback);

    abstract public void onTimeout();

    abstract public void cancel();

    @Override
    public int compareTo(DiagnosticCommand another) {
        int difference = mPriority - another.getPriority();
        if(difference == 0){
            difference = mCommandId - another.getCommandId();
        }
        return Integer.signum(difference);
    }

    public interface CompletionCallback{

        void onComplete();

    }

}
