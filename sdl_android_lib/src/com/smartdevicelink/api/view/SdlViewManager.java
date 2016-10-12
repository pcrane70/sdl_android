package com.smartdevicelink.api.view;

import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.proxy.rpc.SetDisplayLayout;
import com.smartdevicelink.proxy.rpc.Show;

public class SdlViewManager {

    private static final String TAG = SdlViewManager.class.getSimpleName();

    private SdlView mRootView;
    private SdlContext mSdlContext;
    private SdlViewDecoratorVisitorImp mDecoratorVisitor;
    private String mTemplateName = "";

    public SdlViewManager (SdlContext sdlContext){
        mSdlContext = sdlContext;
        mDecoratorVisitor = new SdlViewDecoratorVisitorImp();
    }

    public void setRootView(SdlView view){
        mRootView = view;
        mRootView.setSdlViewManager(this);
        mRootView.setSdlContext(mSdlContext);
    }

    public SdlView getRootView(){
        return mRootView;
    }

    public void setCurrentTemplate(String templateName){
        mTemplateName = templateName;
    }

    public String getCurrentTemplate(){
        return mTemplateName;
    }

    public void updateView(){
        String templateName = mRootView.getTemplateName();
        if(templateName != null && !mTemplateName.equals(templateName)){
            mTemplateName = templateName;
            SetDisplayLayout setDisplayLayout = new SetDisplayLayout();
            setDisplayLayout.setDisplayLayout(templateName);
            mSdlContext.sendRpc(setDisplayLayout);
        }
        mRootView.accept(mDecoratorVisitor);
        mSdlContext.sendRpc(mDecoratorVisitor.getShow());
        mSdlContext.sendRpc(mDecoratorVisitor.getSetMediaClockTimer());
    }

    int registerButtonCallback(SdlButton.OnPressListener listener){
        return mSdlContext.registerButtonCallback(listener);
    }

    void unregisterButtonCallBack(int id){
        mSdlContext.unregisterButtonCallback(id);
    }

    int registerMediaButtonCallback(SdlMediaButton mediaButton){
        return mSdlContext.registerMediaButtonCallback(mediaButton.getMediaButtonName(),mediaButton.getListener());
    }

    public void prepareImages(){
        mRootView.uploadRequiredImages();
    }

}
