package com.smartdevicelink.api.interfaces;

import android.content.Context;
import android.os.Handler;

import com.smartdevicelink.api.SdlActivity;
import com.smartdevicelink.api.file.SdlFileManager;
import com.smartdevicelink.api.view.SdlAudioPassThruDialog;
import com.smartdevicelink.api.view.SdlButton;
import com.smartdevicelink.api.menu.SdlMenu;
import com.smartdevicelink.api.menu.SdlMenuItem;
import com.smartdevicelink.api.permission.SdlPermissionManager;
import com.smartdevicelink.proxy.RPCRequest;
import com.smartdevicelink.proxy.rpc.TTSChunk;

public interface SdlContext {

    void startSdlActivity(Class<? extends SdlActivity> activity, int flags);

    SdlContext getSdlApplicationContext();

    Context getAndroidApplicationContext();

    SdlPermissionManager getSdlPermissionManager();

    SdlFileManager getSdlFileManager();

    int registerButtonCallback(SdlButton.OnPressListener listener);

    void unregisterButtonCallback(int id);

    void registerMenuCallback(int id, SdlMenuItem.SelectListener listener);

    void unregisterMenuCallback(int id);

    void registerAudioPassThruListener(SdlAudioPassThruDialog.ReceiveDataListener listener);

    void unregisterAudioPassThruListener(SdlAudioPassThruDialog.ReceiveDataListener listener);

    boolean sendRpc(RPCRequest request);

    SdlMenu getTopMenu();

    Handler getExecutionHandler();

    boolean sendTextToSpeak(String text);

    boolean sendTextToSpeak(TTSChunk chunk);

}
