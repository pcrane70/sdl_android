package com.smartdevicelink.api.view;

import android.util.Log;

import com.smartdevicelink.proxy.rpc.SetMediaClockTimer;
import com.smartdevicelink.proxy.rpc.Show;
import com.smartdevicelink.proxy.rpc.StartTime;
import com.smartdevicelink.proxy.rpc.enums.UpdateMode;

/**
 * Created by mschwerz on 6/13/16.
 */
public class SdlMediaClockView extends SdlView {

    private static final String TAG = SdlMediaClockView.class.getSimpleName();

    private UpdateMode mUpdateMode= UpdateMode.PAUSE;
    private long mEndTime;
    private long mStartTime;

    public SdlMediaClockView(){

    }

    public void setDuration(long milliSeconds) {
        mEndTime = milliSeconds;
    }

    public void setStartTime(long milliSeconds) {
        mStartTime = milliSeconds;
    }

    public void playFromTo(long startMilliSeconds, long endMilliSeconds){
        mUpdateMode= startMilliSeconds>=endMilliSeconds? UpdateMode.COUNTDOWN:UpdateMode.COUNTUP;
        updateSeconds(startMilliSeconds,endMilliSeconds);
    }

    public void pausePlay(){
        mUpdateMode= UpdateMode.PAUSE;
    }

    public void pausePlay(long startMilliSeconds, long endMilliSeconds){
        pausePlay();
        updateSeconds(startMilliSeconds,endMilliSeconds);
    }

    public void resumePlay(){
        mUpdateMode= UpdateMode.RESUME;
    }

    public boolean isPaused(){
        return mUpdateMode==UpdateMode.CLEAR ||mUpdateMode==UpdateMode.PAUSE;
    }

    @Override
    public void clear() {
        mUpdateMode = UpdateMode.CLEAR;
        mEndTime = 0;
        mStartTime = 0;
    }

    @Override
    void decorate(Show show) {
    }

    void decorate(SetMediaClockTimer timer) {
        timer.setUpdateMode(mUpdateMode);
        timer.setStartTime(millisecondsToStartTime(mStartTime));
        timer.setEndTime(millisecondsToStartTime(mEndTime));
    }

    @Override
    void uploadRequiredImages() {

    }

    @Override
    void accept(SdlViewDecoratorVisitor visitor) {
        visitor.visit(this);
    }

    private StartTime millisecondsToStartTime(long millis) {
        StartTime newStartTime = new StartTime();
        newStartTime.setSeconds((int) (millis / 1000) % 60);
        newStartTime.setMinutes((int) ((millis / (1000 * 60)) % 60));
        newStartTime.setHours((int) ((millis / (1000 * 60 * 60)) % 24));
        return newStartTime;
    }

    private void updateSeconds(long startMilliSeconds, long endMilliSeconds){
        setStartTime(startMilliSeconds);
        setDuration(endMilliSeconds);
    }
}
