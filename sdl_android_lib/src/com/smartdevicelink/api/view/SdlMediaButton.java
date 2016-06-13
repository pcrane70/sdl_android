package com.smartdevicelink.api.view;

import com.smartdevicelink.proxy.rpc.enums.ButtonName;

/**
 * Created by mschwerz on 6/10/16.
 */
public class SdlMediaButton extends SdlButtonBase {
    final ButtonName mMediaButtonName;

    public SdlMediaButton(ButtonName mediaButton, OnPressListener listener){
        super(listener);
        mMediaButtonName= mediaButton;
    }

    public ButtonName getMediaButtonName(){return mMediaButtonName;}
}
