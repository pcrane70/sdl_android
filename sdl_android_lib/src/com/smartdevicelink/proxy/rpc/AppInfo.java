package com.smartdevicelink.proxy.rpc;

import java.util.Hashtable;

import com.smartdevicelink.proxy.RPCStruct;

public class AppInfo extends RPCStruct{
	public static final String KEY_APP_DISPLAY_NAME = "appDisplayName";
	public static final String KEY_APP_BUNDLE_ID = "appBundleID";
	public static final String KEY_APP_VERSION = "appVersion";
	public static final String KEY_APP_ICON = "appIcon";

	public AppInfo() { }

	public AppInfo(Hashtable<String, Object> hash) {
		super(hash);
	}

	public void setAppDisplayName(String appDispName) {
		if (appDispName != null) {
			store.put(KEY_APP_DISPLAY_NAME, appDispName);
		} else {
			store.remove(KEY_APP_DISPLAY_NAME);
		}
	}

	public String getAppDisplayName() {
		return (String) store.get(KEY_APP_DISPLAY_NAME);
	}

	public void setAppBundleId(String appBundleId) {
		if (appBundleId != null) {
			store.put(KEY_APP_BUNDLE_ID, appBundleId);
		} else {
			store.remove(KEY_APP_BUNDLE_ID);
		}
	}

	public String getAppBundleId() {
		return (String) store.get(KEY_APP_BUNDLE_ID);
	}

	public void setAppVersion(String appVersion) {
		if (appVersion != null) {
			store.put(KEY_APP_VERSION, appVersion);
		} else {
			store.remove(KEY_APP_VERSION);
		}
	}

	public String getAppVersion() {
		return (String) store.get(KEY_APP_VERSION);
	}

	public void setAppIcon(String appIcon) {
		if (appIcon != null) {
			store.put(KEY_APP_ICON, appIcon);
		} else {
			store.remove(KEY_APP_ICON);
		}
	}

	public String getAppIcon() {
		return (String) store.get(KEY_APP_ICON);
	}
}
