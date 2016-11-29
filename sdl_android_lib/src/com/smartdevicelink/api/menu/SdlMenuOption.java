package com.smartdevicelink.api.menu;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.smartdevicelink.api.file.SdlFile;
import com.smartdevicelink.api.file.SdlFileManager;
import com.smartdevicelink.api.file.SdlImage;
import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.proxy.RPCResponse;
import com.smartdevicelink.proxy.rpc.AddCommand;
import com.smartdevicelink.proxy.rpc.DeleteCommand;
import com.smartdevicelink.proxy.rpc.Image;
import com.smartdevicelink.proxy.rpc.MenuParams;
import com.smartdevicelink.proxy.rpc.enums.ImageType;
import com.smartdevicelink.proxy.rpc.enums.TriggerSource;
import com.smartdevicelink.proxy.rpc.listeners.OnRPCResponseListener;

import java.util.ArrayList;

public class SdlMenuOption extends SdlMenuItem {

    private static final String TAG = SdlMenuOption.class.getSimpleName();

    private final SdlImage mSdlImage;
    private final SelectListener mListener;
    private boolean isVisible;
    private boolean isRegistered;
    private final ArrayList<String> mVoiceCommands;

    private SdlMenuOption(Builder builder){
        super(builder.mName, builder.mIndex);
        mSdlImage = builder.mSdlImage;
        mListener = builder.mListener;
        mVoiceCommands = builder.mVoiceCommands;
        mParent = builder.mParent;
    }

    @Override
    void update(SdlContext sdlContext, int subMenuId) {
        if(isVisible) sendReplaceCommand(sdlContext, subMenuId);
        if(!isRegistered) registerSelectListener(sdlContext);
        sendAddCommand(sdlContext, subMenuId);
    }

    @Override
    void remove(SdlContext sdlContext) {
        if(isVisible) sendDeleteCommand(sdlContext, null);
        if(isRegistered) unregisterSelectListener(sdlContext);
    }

    @Override
    void registerSelectListener(SdlContext sdlContext) {
        if(!isRegistered) {
            sdlContext.registerMenuCallback(mId, mListener);
            isRegistered = true;
        }
    }

    @Override
    void unregisterSelectListener(SdlContext sdlContext) {
        if(isRegistered) {
            sdlContext.unregisterMenuCallback(mId);
            isRegistered = false;
        }
    }

    @Override
    void registerVoiceCommands(SdlMenuItem item) {
        if(mParent!=null){
            mParent.registerVoiceCommands(item);
        } else {
            Log.w(TAG,"Menu option has null parent while registering");
        }
    }

    @Override
    void unregisterVoiceCommands(SdlMenuItem item) {
        if(mParent!=null){
            mParent.unregisterVoiceCommands(item);
        } else {
            Log.w(TAG,"Menu option has null parent while unregistering");
        }
    }

    @Override
    ArrayList<String> getVoiceCommands() {
        return mVoiceCommands;
    }

    private void sendDeleteCommand(SdlContext sdlContext, OnRPCResponseListener listener) {
        DeleteCommand dc = new DeleteCommand();
        dc.setCmdID(mId);
        dc.setOnRPCResponseListener(listener);
        sdlContext.sendRpc(dc);
        isVisible = false;
    }

    private void sendReplaceCommand(final SdlContext sdlContext, final int subMenuId){
        sendDeleteCommand(sdlContext, new OnRPCResponseListener() {
            @Override
            public void onResponse(int correlationId, RPCResponse response) {
                if(response != null && response.getSuccess()){
                    sendAddCommand(sdlContext, subMenuId);
                }
            }
        });
    }

    private void sendAddCommand(final SdlContext sdlContext, final int rootMenuId) {
        AddCommand ac = new AddCommand();
        ac.setCmdID(mId);

        if(mSdlImage != null){
            Log.d(TAG, "Image is set for command: " + mName);
            SdlFileManager fileManager = sdlContext.getSdlFileManager();
            if(fileManager.isFileOnModule(mSdlImage.getSdlName())) {
                Image image = new Image();
                image.setImageType(ImageType.DYNAMIC);
                image.setValue(mSdlImage.getSdlName());
                ac.setCmdIcon(image);
            } else {
                fileManager.uploadSdlImage(mSdlImage, new SdlFileManager.FileReadyListener() {
                    @Override
                    public void onFileReady(SdlFile sdlFile) {
                        if(isVisible) update(sdlContext, rootMenuId);
                    }

                    @Override
                    public void onFileError(SdlFile sdlFile) {
                    }
                });
            }
        }

        MenuParams mp = new MenuParams();
        mp.setMenuName(mName);
        if(rootMenuId > 0) mp.setParentID(rootMenuId);
        if(mIndex >= 0) mp.setPosition(mIndex);
        ac.setMenuParams(mp);

        sdlContext.sendRpc(ac);
        isVisible = true;
    }

    public static class Builder{
        private SdlImage mSdlImage;
        private final SelectListener mListener;
        private ArrayList<String> mVoiceCommands = new ArrayList<>();
        private final SdlMenuItem mParent;
        private String mName;
        private int mIndex =-1;


        public Builder(@NonNull SelectListener listener, @NonNull SdlMenuManager menuManager, @Nullable SdlMenu sdlMenu){
            mListener = listener;
            if(sdlMenu==null){
                mParent = menuManager.getTopMenu();
            } else {
                mParent = sdlMenu;
            }
        }

        public Builder setName(String name){
            mName = name;
            return this;
        }

        public Builder setImage(SdlImage image){
            mSdlImage = image;
            return this;
        }

        public Builder setVoiceCommands(ArrayList<String> voiceCommands){
            mVoiceCommands.clear();
            mVoiceCommands.addAll(voiceCommands);
            return this;
        }

        public Builder setIndex(int index){
            mIndex = index;
            return this;
        }

        public SdlMenuOption build(){
            return new SdlMenuOption(this);
        }


    }

    public interface SelectListener{

        void onSelect(TriggerSource triggerSource);

    }

}
