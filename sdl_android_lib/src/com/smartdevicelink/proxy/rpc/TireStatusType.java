package com.smartdevicelink.proxy.rpc;

import com.smartdevicelink.proxy.RPCStruct;
import com.smartdevicelink.proxy.rpc.enums.PRNDL;
import com.smartdevicelink.util.DebugTool;

import java.util.Hashtable;

public class TireStatusType extends RPCStruct {
    public static final String KEY_VALUE = "value";
    public static final String KEY_TIMESTAMP = "timestamp";

    public TireStatusType() { }

    public TireStatusType(Hashtable<String, Object> hash) {
        super(hash);
    }

    public void setValue(TireStatus value) {
        if (value != null) {
            store.put(KEY_VALUE, value);
        } else {
            store.remove(KEY_VALUE);
        }
    }

    @SuppressWarnings("unchecked")
    public TireStatus getValue() {
        Object obj = store.get(KEY_VALUE);
        if (obj instanceof TireStatus) {
            return (TireStatus) obj;
        } else if (obj instanceof Hashtable) {
            try {
                return new TireStatus((Hashtable<String, Object>) obj);
            } catch (Exception e) {
                DebugTool.logError("Failed to parse " + getClass().getSimpleName() + "." + KEY_VALUE, e);
            }
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
