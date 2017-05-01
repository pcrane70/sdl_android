package com.smartdevicelink.api.view;

import android.support.annotation.NonNull;

import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.api.interfaces.SdlInteractionResponseListener;
import com.smartdevicelink.api.permission.SdlPermission;
import com.smartdevicelink.proxy.rpc.ScrollableMessage;

import java.util.ArrayList;
import java.util.Collection;

public class SdlInformationDialog {
    private static final int MIN_DURATION = 0;
    private static final int DEFAULT_DURATION = 30000;
    private static final int MAX_DURATION = 65535;
    private final String mTextFields;
    private final int mDuration;
    private final SdlInteractionSender mSender= new SdlInteractionSender(SdlPermission.ScrollableMessage);
    private final SdlInteractionButtonManager mButtonManager;

    private SdlInformationDialog(Builder builder) {
        this.mTextFields= builder.mTextFields;
        this.mDuration= builder.mDuration;
        mButtonManager= new SdlInteractionButtonManager(builder.mButtons);
    }

    private  @NonNull ScrollableMessage createScrollableMessage(SdlContext context) {
        final ScrollableMessage newScrollableMessage= new ScrollableMessage();
        newScrollableMessage.setScrollableMessageBody(mTextFields);
        newScrollableMessage.setTimeout(mDuration);
        newScrollableMessage.setSoftButtons(mButtonManager.registerAllButtons(context));
        return newScrollableMessage;
    }

    public boolean send(SdlContext context, SdlInteractionResponseListener listener){
        SdlContext applicationContext= context.getSdlApplicationContext();
        return mSender.sendInteraction(applicationContext,createScrollableMessage(applicationContext),
                null, mButtonManager.getCleanUpListener(applicationContext,listener));
    }

    public static class Builder{
        private String mTextFields;
        private int mDuration = DEFAULT_DURATION;
        private Collection<SdlButton> mButtons = new ArrayList<>();

        /**
         * Sets the duration that the {@link SdlAlertBase} will show up for.
         * The min value is 3000 and the max value is 10000
         * @param duration The amount of milliseconds the SdlCommonAlert should appear
         * @return The builder for the {@link SdlAlertBase}
         */
        public Builder setDuration(int duration){
            if(duration < MIN_DURATION) {
                mDuration = MIN_DURATION;
            } else if(duration < MAX_DURATION){
                mDuration = duration;
            } else {
                mDuration = MAX_DURATION;
            }
            return this;
        }

        public Builder setText(String text) {
            mTextFields= text;
            return this;
        }

        public Builder setButtons(@NonNull Collection<SdlButton> buttons){
            mButtons.clear();
            mButtons.addAll(buttons);
            return this;
        }

        public Builder addButton(@NonNull SdlButton button){
            mButtons.add(button);
            return this;
        }

        public SdlInformationDialog build() {
                return new SdlInformationDialog(this);
            }
        }

}
