package com.smartdevicelink.api.diagnostics;

import com.smartdevicelink.api.interfaces.SdlContext;

import java.util.ArrayList;

public class DIDBatchCommand extends DiagnosticCommand{

    private DIDBatchListener mBatchListener;
    private ArrayList<ReadDidCommand> mDidCommands = new ArrayList<>();
    private ArrayList<DID> mDidResults = new ArrayList<>();
    private int position = 0;

    public DIDBatchCommand(SdlContext sdlContext, int timeout, int priority, DIDBatchListener listener){
        super(sdlContext, timeout, priority);
        mBatchListener = listener;
    }

    public void addCommand(ReadDidCommand command){
        command.setListener(new DIDReadListener() {
            @Override
            public void onReadComplete(DID did) {
                addResult(did);
            }

            @Override
            public void onTimeout(DID did) {
                addResult(did);
            }

            @Override
            public void onCanceled(DID did) {
                addResult(did);
            }

            private void addResult(DID did) {
                mDidResults.add(did);
                position++;
                if(position == mDidCommands.size()){
                    mBatchListener.onBatchComplete(mDidResults);
                    mDidCommands.clear();
                }
            }
        });
        mDidCommands.add(command);
    }

    @Override
    public boolean isFinished() {
        return mDidCommands.isEmpty();
    }

    @Override
    public void execute(CompletionCallback callback) {
        if(position < mDidCommands.size()){
            mDidCommands.get(position).execute(callback);
        }
    }

    @Override
    public void onTimeout() {
        mDidCommands.get(position).onTimeout();
    }

    @Override
    public void cancel() {
        mDidCommands.get(position).cancel();
    }
}
