package com.smartdevicelink.api.view;

/**
 * Created by mschwerz on 6/13/16.
 */
public interface SdlViewDecoratorVisitor {

    void visit(SdlView view);
    void visit(SdlMediaClockView view);
}
