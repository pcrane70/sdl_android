package com.smartdevicelink.api.diagnostics;

import com.smartdevicelink.api.interfaces.SdlContext;

public abstract class DiagnosticCommand implements Comparable<DiagnosticCommand>{

    protected int mTimeout;
    protected int mPriority;
    protected int mCommandId;

    public DiagnosticCommand(SdlContext sdlContext, int timeout, int priority){
        mTimeout = timeout;
        mPriority = priority;

    }

    public int getTimeout() {
        return mTimeout;
    }

    public int getPriority() {
        return mPriority;
    }

    public int getCommandId() {
        return mCommandId;
    }

    public void setCommandId(int commandId){
        mCommandId = commandId;
    }

    abstract public void execute(CompletionCallback callback);

    abstract public void onTimeout();

    abstract public void cancel();

    @Override
    public int compareTo(DiagnosticCommand another) {
        int difference = mPriority - another.getPriority();
        if(difference == 0){
            difference = mTimeout - another.getTimeout();
        }
        return Integer.signum(difference);
    }

    public interface CompletionCallback{

        void onComplete();

    }

}
