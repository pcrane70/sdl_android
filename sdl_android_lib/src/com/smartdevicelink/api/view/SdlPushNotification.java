package com.smartdevicelink.api.view;

public class SdlPushNotification extends SdlAlertBase {
    private static final String TAG = SdlPushNotification.class.getSimpleName();

    private SdlPushNotification(Builder builder) {
        super(builder);
    }

    public static class Builder extends SdlAlertBase.Builder<Builder>{

        public Builder() {

        }

        @Override
        protected Builder grabBuilder() {
            return this;
        }

        @Override
        public SdlPushNotification build(){
            return new SdlPushNotification(this);
        }
    }

}
