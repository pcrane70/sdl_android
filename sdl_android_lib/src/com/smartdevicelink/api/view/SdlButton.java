package com.smartdevicelink.api.view;

import com.smartdevicelink.api.file.SdlImage;
import com.smartdevicelink.proxy.rpc.enums.SystemAction;

public class SdlButton {
    private final OnPressListener mListener;
    private int mId;
    private String mText;
    private SdlImage mSdlImage;
    private boolean isGraphicOnly;
    private boolean isHighlighted;
    private SystemAction mSystemAction;

    public SdlButton(String text, OnPressListener listener){
        mText = text;
        mListener = listener;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public SdlImage getSdlImage() {
        return mSdlImage;
    }

    public void setSdlImage(SdlImage sdlImage) {
        this.mSdlImage = sdlImage;
    }

    public boolean isGraphicOnly() {
        return isGraphicOnly;
    }

    public void setGraphicOnly(boolean graphicOnly) {
        isGraphicOnly = graphicOnly;
    }

    public boolean isHighlighted() {
        return isHighlighted;
    }

    public void setHighlighted(boolean highlighted) {
        isHighlighted = highlighted;
    }

    public SystemAction getSystemAction() {
        return mSystemAction;
    }

    public void setSystemAction(SystemAction systemAction) {
        mSystemAction = systemAction;
    }

    public OnPressListener getListener() {
        return mListener;
    }

    public interface OnPressListener {
        void onButtonPress();
    }
}