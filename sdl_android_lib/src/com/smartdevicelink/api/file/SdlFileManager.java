package com.smartdevicelink.api.file;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.smartdevicelink.api.SdlApplication;
import com.smartdevicelink.api.SdlApplicationConfig;
import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.proxy.RPCResponse;
import com.smartdevicelink.proxy.rpc.ListFiles;
import com.smartdevicelink.proxy.rpc.ListFilesResponse;
import com.smartdevicelink.proxy.rpc.PutFile;
import com.smartdevicelink.proxy.rpc.SetAppIcon;
import com.smartdevicelink.proxy.rpc.enums.FileType;
import com.smartdevicelink.proxy.rpc.listeners.OnRPCResponseListener;

import java.io.ByteArrayOutputStream;
import java.util.HashSet;
import java.util.List;

public class SdlFileManager implements SdlApplication.LifecycleListener{

    private static final String TAG = SdlFileManager.class.getSimpleName();

    private final HashSet<String> mFileSet;

    private final SdlContext mSdlContext;
    private final SdlImage mAppIcon;

    public SdlFileManager(SdlContext sdlContext, SdlApplicationConfig config){
        mSdlContext = sdlContext;
        mAppIcon = config.getAppIcon();
        mFileSet = new HashSet<>();
    }

    public boolean uploadSdlImage(@NonNull final SdlImage sdlImage, @Nullable final FileReadyListener listener){
        Log.d(TAG, "SdlImage isForceReplace = " + sdlImage.isForceReplace());
        if(!sdlImage.isForceReplace() && mFileSet.contains(sdlImage.getSdlName())) return true;

        if(sdlImage.getResId() != null) {

            PutFile file = new PutFile();
            file.setFileType(FileType.GRAPHIC_PNG);
            file.setSdlFileName(sdlImage.getSdlName());
            file.setPersistentFile(sdlImage.isPersistent());
            Bitmap image = BitmapFactory.decodeResource(mSdlContext.getAndroidApplicationContext().getResources(), sdlImage.getResId());
            ByteArrayOutputStream bas = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, bas);
            byte[] data = bas.toByteArray();
            file.setBulkData(data);

            if(listener != null) {
                file.setOnRPCResponseListener(new OnRPCResponseListener() {
                    @Override
                    public void onResponse(int correlationId, RPCResponse response) {
                        if (response.getSuccess()) {
                            mFileSet.add(sdlImage.getSdlName());
                            listener.onFileReady(sdlImage);
                        } else {
                            listener.onFileError(sdlImage);
                        }
                    }
                });
            }

            mSdlContext.sendRpc(file);
        }
        return false;
    }

    public boolean isFileOnModule(String sdlFileName){
        return mFileSet.contains(sdlFileName);
    }

    @Override
    public void onSdlConnect() {
        Log.d(TAG, "onSdlConnect");
        if(mAppIcon != null){
            sendListFiles();
        }
    }

    @Override
    public void onBackground() {

    }

    @Override
    public void onForeground() {

    }

    @Override
    public void onExit() {

    }

    @Override
    public void onSdlDisconnect() {

    }

    private void uploadAppIcon(){
        uploadSdlImage(mAppIcon, new FileReadyListener() {
            @Override
            public void onFileReady(SdlFile sdlFile) {
                setAppIcon();
            }

            @Override
            public void onFileError(SdlFile sdlFile) {
                Log.d(TAG, "Unable to upload App Icon.");
            }
        });
    }

    private void setAppIcon() {
        SetAppIcon sai = new SetAppIcon();
        sai.setSdlFileName(mAppIcon.getSdlName());
        mSdlContext.sendRpc(sai);
    }

    private void sendListFiles(){
        ListFiles listFiles = new ListFiles();
        listFiles.setOnRPCResponseListener(mListFileResponseListener);
        mSdlContext.sendRpc(listFiles);
    }

    private OnRPCResponseListener mListFileResponseListener = new OnRPCResponseListener() {
        @Override
        public void onResponse(int correlationId, RPCResponse response) {
            if(response == null) return;
            ListFilesResponse lfr = (ListFilesResponse) response;
            List<String> fileNames = lfr.getFilenames();
            if(fileNames != null) {
                for (String fileName : fileNames) {
                    mFileSet.add(fileName);
                }
            }

            if(mAppIcon.isForceReplace() ||
                    !mFileSet.contains(mAppIcon.getSdlName())){
                uploadAppIcon();
            } else {
                setAppIcon();
            }
        }
    };

    public interface FileReadyListener{

        void onFileReady(SdlFile sdlFile);

        void onFileError(SdlFile sdlFile);

    }
}
