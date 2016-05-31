package com.smartdevicelink.api;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.smartdevicelink.transport.SdlBroadcastReceiver;

public class SdlBluetoothReceiver extends SdlBroadcastReceiver{

    private static final String TAG = SdlBluetoothReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Bluetooth Action: " + intent.getAction());
        if(intent.getAction().compareTo(BluetoothDevice.ACTION_ACL_CONNECTED) == 0){
            SdlManager.getInstance().onBluetoothConnected();
        }
    }

    @Override
    public Class defineLocalSdlRouterClass() {
        return SdlManager.getInstance().getRouterServiceClass();
    }

    @Override
    public void onSdlEnabled(Context context, Intent intent) {
        SdlManager.getInstance().onBluetoothConnected();
    }
}
