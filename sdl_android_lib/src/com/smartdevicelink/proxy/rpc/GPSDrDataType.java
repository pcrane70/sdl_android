package com.smartdevicelink.proxy.rpc;

import com.smartdevicelink.proxy.RPCStruct;
import com.smartdevicelink.util.DebugTool;

import java.util.Hashtable;

public class GPSDrDataType extends RPCStruct {
    public static final String KEY_VALUE = "value";
    public static final String KEY_TIMESTAMP = "timestamp";

    public GPSDrDataType() { }

    public GPSDrDataType(Hashtable<String, Object> hash) {
        super(hash);
    }

    public void setValue(GPSDrData gpsDrData) {
        if (gpsDrData != null) {
            store.put(KEY_VALUE, gpsDrData);
        } else {
            store.remove(KEY_VALUE);
        }
    }

    @SuppressWarnings("unchecked")
    public GPSDrData getValue() {
        Object obj = store.get(KEY_VALUE);
        if (obj instanceof GPSDrData) {
            return (GPSDrData) obj;
        } else if (obj instanceof Hashtable) {
            GPSDrData theCode = null;
            try {
                theCode = new GPSDrData((Hashtable<String, Object>) obj);
            } catch (Exception e) {
                DebugTool.logError("Failed to parse " + getClass().getSimpleName() + "." + KEY_VALUE, e);
            }
            return theCode;
        }
        return null;
    }

    public void setTimeStamp(TimeStampType timeStamp) {
        if (timeStamp != null) {
            store.put(KEY_TIMESTAMP, timeStamp);
        } else {
            store.remove(KEY_TIMESTAMP);
        }
    }

    @SuppressWarnings("unchecked")
    public TimeStampType getTimeStamp() {
        Object obj = store.get(KEY_TIMESTAMP);
        if (obj instanceof TimeStampType) {
            return (TimeStampType) obj;
        } else if (obj instanceof Hashtable) {
            try {
                return new TimeStampType((Hashtable<String, Object>) obj);
            } catch (Exception e) {
                DebugTool.logError("Failed to parse " + getClass().getSimpleName() + "." + KEY_TIMESTAMP, e);
            }
        }
        return null;
    }
}
