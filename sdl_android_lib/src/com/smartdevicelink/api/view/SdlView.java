package com.smartdevicelink.api.view;

import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.proxy.rpc.DisplayCapabilities;
import com.smartdevicelink.proxy.rpc.Show;

public abstract class SdlView {

    protected SdlViewManager mViewManager;
    protected DisplayCapabilities mDisplayCapabilities;
    protected SdlContext mSdlContext;
    protected boolean isVisible;
    protected boolean isChanged = true;

    public final void redraw(){
        mViewManager.updateView();
    }

    public void setDisplayCapabilities(DisplayCapabilities displayCapabilities){
        mDisplayCapabilities = displayCapabilities;
    }

    final public boolean isGraphicsSupported() {
        return mDisplayCapabilities != null &&
                mDisplayCapabilities.getGraphicSupported() != null &&
                mDisplayCapabilities.getGraphicSupported();
    }

    final public int getTextFieldCount(){
        return mDisplayCapabilities.getTextFields() != null ?
                mDisplayCapabilities.getTextFields().size(): 0;
    }

    public void setSdlViewManager(SdlViewManager sdlViewManager){
        mViewManager = sdlViewManager;
    }

    public void setSdlContext(SdlContext sdlContext){
        mSdlContext = sdlContext;
    }

    public abstract void clear();

    boolean decorate(Show show){
      if(isChanged && isVisible){
          isChanged = false;
          return true;
      } else {
          return false;
      }
    }

    abstract void uploadRequiredImages();

    public String getTemplateName(){
        return null;
    }

    public void setIsVisible(boolean isVisible){
        this.isVisible = isVisible;
        if(isVisible){
            isChanged = true;
        }
    }

}
