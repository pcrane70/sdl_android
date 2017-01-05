package com.smartdevicelink.proxy.rpc;

import com.smartdevicelink.proxy.RPCStruct;
import com.smartdevicelink.util.DebugTool;

import java.util.Hashtable;

public class WheelSpeedsDataType extends RPCStruct {
    public static final String KEY_VALUE = "value";
    public static final String KEY_TIMESTAMP = "timestamp";

    public WheelSpeedsDataType() { }

    public WheelSpeedsDataType(Hashtable<String, Object> hash) {
        super(hash);
    }

    public void setValue(WheelSpeedsData value) {
        if (value != null) {
            store.put(KEY_VALUE, value);
        } else {
            store.remove(KEY_VALUE);
        }
    }

    @SuppressWarnings("unchecked")
    public WheelSpeedsData getValue() {
        Object obj = store.get(KEY_VALUE);
        if (obj instanceof WheelSpeedsData) {
            return (WheelSpeedsData) obj;
        } else if (obj instanceof Hashtable) {
            try {
                return new WheelSpeedsData((Hashtable<String, Object>) obj);
            } catch (Exception e) {
                DebugTool.logError("Failed to parse " + getClass().getSimpleName() + "." + KEY_VALUE, e);
            }
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
