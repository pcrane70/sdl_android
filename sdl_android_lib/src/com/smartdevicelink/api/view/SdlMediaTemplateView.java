package com.smartdevicelink.api.view;

import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.proxy.rpc.DisplayCapabilities;
import com.smartdevicelink.proxy.rpc.Show;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by mschwerz on 6/13/16.
 */
public class SdlMediaTemplateView extends SdlView {


    private SdlTextView mSdlTextView;
    private SdlGraphicView mSdlGraphicView;
    private SdlButtonView mSdlButtonView;
    private ArrayList<SdlMediaButton> mSdlMediaButtons= new ArrayList<>();
    private SdlMediaClockView mSdlMediaClockView;

    public SdlMediaTemplateView(){
    }

    public void setSdlTextView(SdlTextView view){
        mSdlTextView= view;
        setViewContextInformation(mSdlTextView);
    }

    public void setSdlGraphicView(SdlGraphicView view){
        mSdlGraphicView= view;
        setViewContextInformation(mSdlGraphicView);
    }

    public void setSdlButtonView(SdlButtonView view){
        mSdlButtonView= view;
        setViewContextInformation(mSdlButtonView);
    }

    public void setSdlMediaButtons(Collection<SdlMediaButton> buttons){
        //unregister beforehand?
        mSdlMediaButtons.clear();
        for(SdlMediaButton button:buttons){
            addSdlMediaButton(button);
        }
    }

    public void addSdlMediaButton(SdlMediaButton button){
        mSdlMediaButtons.add(button);
        if(mViewManager!=null){
            mViewManager.registerMediaButtonCallback(button);
        }
    }

    public void setSdlMediaClock(SdlMediaClockView view){
        mSdlMediaClockView= view;
        setViewContextInformation(mSdlMediaClockView);
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
        if(mSdlMediaClockView !=null){
            mSdlMediaClockView.setDisplayCapabilities(mDisplayCapabilities);
        }
    }

    @Override
    public String getTemplateName() {
        return SdlTemplateView.LayoutTemplate.MEDIA.toString();
    }

    @Override
    public void accept(SdlViewDecoratorVisitor visitor) {
        if(mSdlTextView != null) {
            mSdlTextView.accept(visitor);
        }
        if(mSdlGraphicView != null) {
            mSdlGraphicView.accept(visitor);
        }
        if(mSdlButtonView != null) {
            mSdlButtonView.accept(visitor);
        }
        if(mSdlMediaClockView !=null){
            mSdlMediaClockView.accept(visitor);
        }
        visitor.visit(this);
    }

    @Override
    public void setSdlContext(SdlContext sdlContext) {
        mSdlContext = sdlContext;
        if(mSdlTextView != null){
            mSdlTextView.setSdlViewManager(mViewManager);
        }
        if(mSdlGraphicView != null){
            mSdlGraphicView.setSdlViewManager(mViewManager);
        }
        if(mSdlButtonView != null){
            mSdlButtonView.setSdlViewManager(mViewManager);
        }
        if(mSdlMediaClockView !=null){
            mSdlMediaClockView.setSdlViewManager(mViewManager);
        }
    }

    @Override
    public void setIsVisible(boolean isVisible) {
        super.setIsVisible(isVisible);
        if(mSdlTextView != null){
            mSdlTextView.setIsVisible(isVisible);
        }
        if(mSdlGraphicView != null){
            mSdlGraphicView.setIsVisible(isVisible);
        }
        if(mSdlButtonView != null){
            mSdlButtonView.setIsVisible(isVisible);
        }
        if(mSdlMediaClockView !=null){
            mSdlMediaClockView.setSdlViewManager(mViewManager);
        }
    }


    @Override
    public void clear() {

    }

    @Override
    public void decorate(Show show) {

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
        if(mSdlMediaClockView !=null){
            mSdlMediaClockView.uploadRequiredImages();
        }
    }

    void setViewContextInformation(SdlView view){
        view.setSdlContext(mSdlContext);
        view.setSdlViewManager(mViewManager);
        view.setDisplayCapabilities(mDisplayCapabilities);
    }
}
