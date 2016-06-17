package com.smartdevicelink.api.properties;

import com.smartdevicelink.api.file.SdlImage;
import com.smartdevicelink.proxy.rpc.Image;
import com.smartdevicelink.proxy.rpc.SetGlobalProperties;
import com.smartdevicelink.proxy.rpc.enums.ImageType;

/**
 * Created by mschwerz on 6/15/16.
 */
public class SdlMenuProperties {
    private final String mMenuTitle;
    private final SdlImage mMenuIcon;

    public SdlMenuProperties(String menuTitle, SdlImage menuIcon){
        mMenuTitle= menuTitle;
        mMenuIcon= menuIcon;
    }


    public SetGlobalProperties decorateProperties(SetGlobalProperties toDecorateProperties){
        if(mMenuTitle!=null)
            toDecorateProperties.setMenuTitle(mMenuTitle);
        if(mMenuIcon!=null){
            Image newImage= new Image();
            newImage.setImageType(ImageType.DYNAMIC);
            newImage.setValue(mMenuIcon.getSdlName());
        }
        return toDecorateProperties;
    }

}
