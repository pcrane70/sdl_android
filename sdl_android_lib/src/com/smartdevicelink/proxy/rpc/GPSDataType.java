package com.smartdevicelink.proxy.rpc;

import com.smartdevicelink.proxy.RPCStruct;
import com.smartdevicelink.util.DebugTool;

import java.util.Hashtable;

public class GPSDataType extends RPCStruct {
    public static final String KEY_VALUE = "value";
    public static final String KEY_TIMESTAMP = "timestamp";

    public GPSDataType() { }

    public GPSDataType(Hashtable<String, Object> hash) {
        super(hash);
    }

    public void setValue(GPSData gps) {
        if (gps != null) {
            store.put(KEY_VALUE, gps);
        } else {
            store.remove(KEY_VALUE);
        }
    }

    @SuppressWarnings("unchecked")
    public GPSData getValue() {
        Object obj = store.get(KEY_VALUE);
        if (obj instanceof GPSData) {
            return (GPSData) obj;
        } else if (obj instanceof Hashtable) {
            GPSData theCode = null;
            try {
                theCode = new GPSData((Hashtable<String, Object>) obj);
            } catch (Exception e) {
                DebugTool.logError("Failed to parse " + getClass().getSimpleName() + "." + KEY_VALUE, e);
            }
            return theCode;
        }
        return null;
    }

    public void setTimeStamp(Integer timeStamp) {
        if (timeStamp != null) {
            store.put(KEY_TIMESTAMP, timeStamp);
        } else {
            store.remove(KEY_TIMESTAMP);
        }
    }

    public Integer getTimeStamp() {
        return (Integer) store.get(KEY_TIMESTAMP);
    }
}
