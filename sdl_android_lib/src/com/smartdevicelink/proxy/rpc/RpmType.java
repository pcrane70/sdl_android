package com.smartdevicelink.proxy.rpc;

import com.smartdevicelink.proxy.RPCStruct;
import com.smartdevicelink.util.DebugTool;

import java.util.Hashtable;

public class RpmType extends RPCStruct {
    public static final String KEY_VALUE = "value";
    public static final String KEY_TIMESTAMP = "timestamp";

    public RpmType() { }

    public RpmType(Hashtable<String, Object> hash) {
        super(hash);
    }

    public void setValue(Integer value) {
        if (value != null) {
            store.put(KEY_VALUE, value);
        } else {
            store.remove(KEY_VALUE);
        }
    }

    public Integer getValue() {
        return (Integer) store.get(KEY_VALUE);
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
