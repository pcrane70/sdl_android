package com.smartdevicelink.api.view;

import com.smartdevicelink.api.file.SdlImage;

public class SdlButton extends SdlButtonBase{

    private final String mText;
    private SdlImage mSdlImage;
    private boolean isGraphicOnly;

    public SdlButton(String text, OnPressListener listener){
        super(listener);
        mText = text;
    }

    public SdlImage getSdlImage() {
        return mSdlImage;
    }

    public void setSdlImage(SdlImage sdlImage) {
        mSdlImage = sdlImage;
    }

    public String getText() {
        return mText;
    }

    public boolean isGraphicOnly() {
        return isGraphicOnly;
    }

    public void setGraphicOnly(boolean graphicOnly) {
        isGraphicOnly = graphicOnly;
    }
}
