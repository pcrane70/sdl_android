package com.smartdevicelink.api.view;

import android.util.Log;

import com.smartdevicelink.proxy.rpc.DisplayCapabilities;
import com.smartdevicelink.proxy.rpc.Show;
import com.smartdevicelink.proxy.rpc.enums.UpdateMode;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by mschwerz on 6/13/16.
 */
public class SdlMediaTemplateView extends SdlView {


    private SdlTextView mSdlTextView;
    private SdlGraphicView mSdlGraphicView;
    private SdlButtonView mSdlButtonView;
    private ArrayList<SdlMediaButton> mSdlMediaButtons;
    //Media Clock timer

    SdlMediaTemplateView(Builder builder){
        mSdlTextView= builder.mSdlTextView;
        mSdlGraphicView= builder.mSdlGraphicView;
        mSdlButtonView= builder.mSdlButtonView;
        mSdlMediaButtons= builder.mSdlMediaButtons;
        for(SdlMediaButton button:mSdlMediaButtons)
            mViewManager.registerMediaButtonCallback(button);
    }

    @Override
    public void setDisplayCapabilities(DisplayCapabilities displayCapabilities) {
        super.setDisplayCapabilities(displayCapabilities);
        if(mSdlTextView != null){
            mSdlTextView.setDisplayCapabilities(mDisplayCapabilities);
        }
        if(mSdlGraphicView != null){
            mSdlGraphicView.setDisplayCapabilities(mDisplayCapabilities);
        }
        if(mSdlButtonView != null){
            mSdlButtonView.setDisplayCapabilities(mDisplayCapabilities);
        }
    }

    @Override
    public String getTemplateName() {
        return SdlTemplateView.LayoutTemplate.MEDIA.toString();
    }


    @Override
    public void clear() {

    }

    @Override
    public void decorate(Show show) {
        if(mSdlTextView != null) {
            mSdlTextView.decorate(show);
        }
        if(mSdlGraphicView != null) {
            mSdlGraphicView.decorate(show);
        }
        if(mSdlButtonView != null) {
            mSdlButtonView.decorate(show);
        }
    }

    @Override
    void uploadRequiredImages() {
        if(mSdlTextView != null) {
            mSdlTextView.uploadRequiredImages();
        }
        if(mSdlGraphicView != null) {
            mSdlGraphicView.uploadRequiredImages();
        }
        if(mSdlButtonView != null) {
            mSdlButtonView.uploadRequiredImages();
        }
    }

    public class SdlMediaClock extends SdlView{

        private UpdateMode mUpdateMode;
        private long mEndTime;
        private long mStartTime;

        SdlMediaClock(UpdateMode updateMode){
            mUpdateMode= updateMode;
        }

        public void setDuration(long milliSeconds){
            mEndTime= milliSeconds;
        }

        public void setStartTime(long milliSeconds){
            mStartTime= milliSeconds;
        }

        @Override
        public void clear() {
            mUpdateMode= UpdateMode.CLEAR;
            mEndTime=0;
            mStartTime=0;
        }

        @Override
        void decorate(Show show) {
            //can decorate the show? (though method is deprecated...)
        }

        @Override
        void uploadRequiredImages() {

        }
    }

    public static class Builder{
        private SdlTextView mSdlTextView;
        private SdlGraphicView mSdlGraphicView;
        private SdlButtonView mSdlButtonView;
        private ArrayList<SdlMediaButton> mSdlMediaButtons= new ArrayList<>();

        public Builder(){

        }

        public Builder setSdlTextView(SdlTextView view){
           mSdlTextView= view;
            return this;
        }

        public Builder setSdlGraphicView(SdlGraphicView view){
            mSdlGraphicView= view;
            return this;
        }

        public Builder setSdlButtonView(SdlButtonView view){
            mSdlButtonView= view;
            return this;
        }

        public Builder setSdlMediaButtons(Collection<SdlMediaButton> buttons){
            mSdlMediaButtons.clear();
            mSdlMediaButtons.addAll(buttons);
            return this;
        }

        public SdlMediaTemplateView build(){
            return new SdlMediaTemplateView(this);
        }
    }
}
