package com.smartdevicelink.proxy.rpc;

import com.smartdevicelink.proxy.RPCStruct;
import com.smartdevicelink.util.SdlDataTypeConverter;

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
