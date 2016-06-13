package com.smartdevicelink.api.view;

import com.smartdevicelink.proxy.rpc.SetMediaClockTimer;
import com.smartdevicelink.proxy.rpc.Show;

/**
 * Created by mschwerz on 6/13/16.
 */
public class SdlViewDecoratorVisitorImp implements SdlViewDecoratorVisitor {
    Show mShow= new Show();
    SetMediaClockTimer mMediaClockTimer= new SetMediaClockTimer();


    SdlViewDecoratorVisitorImp(){

    }

    @Override
    public void visit(SdlView view) {
        view.decorate(mShow);
    }

    @Override
    public void visit(SdlMediaClockView view) {
        view.decorate(mShow);
        view.decorate(mMediaClockTimer);
    }

    Show getShow() {
        return mShow;
    }

    SetMediaClockTimer getSetMediaClockTimer() {
        return mMediaClockTimer;
    }
}
