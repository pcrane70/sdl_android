package com.smartdevicelink.api.view;

/**
 * Created by mschwerz on 6/10/16.
 */
public abstract class SdlButtonBase {

    final protected OnPressListener mListener;
    protected int mId;

    protected SdlButtonBase(OnPressListener listener){mListener=listener;}

    int getId() {
        return mId;
    }

    void setId(int id) {
        mId = id;
    }

    public final OnPressListener getListener() {
        return mListener;
    }

    public interface OnPressListener {

        void onButtonPress();

    }

}
