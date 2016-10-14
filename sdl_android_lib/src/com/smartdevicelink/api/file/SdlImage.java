package com.smartdevicelink.api.file;

import android.graphics.Bitmap;

import java.io.File;
import java.net.URL;

public class SdlImage implements SdlFile {

    private final String mSdlName;
    private final Integer mResId;
    private final URL mURL;
    private final File mPath;
    private final Bitmap mBitmap;
    private final boolean isPersistent;
    private final boolean forceReplace;
    private final boolean isStaticImage;

    public SdlImage(String sdlName) {
        this.mSdlName = sdlName;
        this.mResId = null;
        this.mURL = null;
        this.mPath = null;
        this.mBitmap = null;
        this .isPersistent = false;
        this.forceReplace = false;
        this.isStaticImage = true;
    }

    public SdlImage(String sdlName, Integer resId, boolean isPersistent, boolean forceReplace){
        this.mSdlName = sdlName;
        this.mResId = resId;
        this.mURL = null;
        this.mPath = null;
        this.mBitmap = null;
        this.isPersistent = isPersistent;
        this.forceReplace = forceReplace;
        this.isStaticImage = false;
    }

    public SdlImage(String sdlName, URL url, boolean isPersistent, boolean forceReplace){
        this.mSdlName = sdlName;
        this.mResId = null;
        this.mURL = url;
        this.mPath = null;
        this.mBitmap = null;
        this.isPersistent = isPersistent;
        this.forceReplace = forceReplace;
        this.isStaticImage = false;
    }

    public SdlImage(String sdlName, File path, boolean isPersistent, boolean forceReplace){
        this.mSdlName = sdlName;
        this.mResId = null;
        this.mURL = null;
        this.mPath = path;
        this.mBitmap = null;
        this.isPersistent = isPersistent;
        this.forceReplace = forceReplace;
        this.isStaticImage = false;
    }

    public SdlImage(String sdlName, Bitmap bitmap, boolean isPersistent, boolean forceReplace){
        this.mSdlName = sdlName;
        this.mResId = null;
        this.mURL = null;
        this.mPath = null;
        this.mBitmap = bitmap;
        this.isPersistent = isPersistent;
        this.forceReplace = forceReplace;
        this.isStaticImage = false;
    }

    @Override
    public String getSdlName() {
        return mSdlName;
    }

    @Override
    public Integer getResId() {
        return mResId;
    }

    @Override
    public URL getURL() {
        return mURL;
    }

    @Override
    public File getPath() {
        return mPath;
    }

    @Override
    public boolean isPersistent() {
        return isPersistent;
    }

    @Override
    public boolean isForceReplace() {
        return forceReplace;
    }

    public boolean isStaticImage() { return isStaticImage; }

    public Bitmap getBitmap() { return mBitmap; }
}
