package com.smartdevicelink.api.properties;

import com.smartdevicelink.api.file.SdlImage;
import com.smartdevicelink.proxy.rpc.Image;
import com.smartdevicelink.proxy.rpc.VrHelpItem;
import com.smartdevicelink.proxy.rpc.enums.ImageType;

/**
 * Created by mschwerz on 6/15/16.
 */
public class SdlVRHelpItem {
    private final String mDisplayText;
    private final SdlImage mSdlImage;

    public SdlVRHelpItem(String displayText, SdlImage image) {
        mDisplayText = displayText;
        mSdlImage = image;
    }

    public SdlImage getSdlImage(){return mSdlImage;}
    public String getDisplayText(){return mDisplayText;}

    public VrHelpItem createHelpItem(int position){
        VrHelpItem newHelpItem= new VrHelpItem();
        newHelpItem.setPosition(position);
        newHelpItem.setText(mDisplayText);
        Image newImage= new Image();
        newImage.setImageType(ImageType.DYNAMIC);
        newImage.setValue(mSdlImage.getSdlName());
        newHelpItem.setImage(newImage);
        return newHelpItem;
    }
}
