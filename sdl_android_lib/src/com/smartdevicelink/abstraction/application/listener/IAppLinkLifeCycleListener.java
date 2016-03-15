package com.smartdevicelink.abstraction.application.listener;

import com.smartdevicelink.abstraction.constants.SDLVersion;
import com.smartdevicelink.proxy.rpc.OnLockScreenStatus;


public interface IAppLinkLifeCycleListener {
	
	void onAppLinkConnected(SDLVersion sdlVersion);
	void onAppLinkDisconnect();
	void onLockScreenNotification(OnLockScreenStatus status);

}
