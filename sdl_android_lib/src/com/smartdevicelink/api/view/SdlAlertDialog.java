package com.smartdevicelink.api.view;

import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.api.permission.SdlPermission;

public class SdlAlertDialog extends SdlAlertBase{
    private final String TAG = getClass().getSimpleName();

    private SdlAlertDialog(Builder builder) {
        super(builder);
    }

    @Override
    protected SdlInteractionSender getSender() {
        if(mSender==null)
            mSender= new SdlAlertDialogSender(SdlPermission.Alert);
        return mSender;
    }

    private class SdlAlertDialogSender extends SdlInteractionSender{
        private SdlAlertDialogSender(SdlPermission permission) {
            super(permission);
        }

        @Override
        protected boolean isAbleToSendInteraction(SdlPermission permission, SdlContext context) {
            //TODO: Be able to know when the app is in foreground
            return super.isAbleToSendInteraction(permission, context) && true;
        }
    }

    //Extends the common Builder for the Alerts
    public static class Builder extends SdlAlertBase.Builder<Builder>{

        public Builder() {

        }

        //Ensures that if we want to add other methods to this builder that we return
        //the concrete child instead of the abstract parent within the parent builder
        @Override
        protected Builder grabBuilder() {
            return this;
        }

        @Override
        public SdlAlertDialog build(){
            return new SdlAlertDialog(this);
        }
    }

}
