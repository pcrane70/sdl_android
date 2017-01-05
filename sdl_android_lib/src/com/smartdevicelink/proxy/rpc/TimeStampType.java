package com.smartdevicelink.proxy.rpc;

import com.smartdevicelink.proxy.RPCStruct;
import com.smartdevicelink.util.SdlDataTypeConverter;

import java.util.Hashtable;

public class TimeStampType extends RPCStruct {
    public static final String KEY_SECOND = "second";
    public static final String KEY_MICRO_SECOND = "microSecond";

    public TimeStampType() { }

    public TimeStampType(Hashtable<String, Object> hash) {
        super(hash);
    }

    public void setSecond(Integer second) {
        if (second != null) {
            store.put(KEY_SECOND, second);
        } else {
            store.remove(KEY_SECOND);
        }
    }

    public Integer getSecond() {
        return (Integer) store.get(KEY_SECOND);
    }

    public void setMicroSecond(Integer microSecond) {
        if (microSecond != null) {
            store.put(KEY_MICRO_SECOND, microSecond);
        } else {
            store.remove(KEY_MICRO_SECOND);
        }
    }

    public Integer getMicroSecond() {
        return (Integer) store.get(KEY_MICRO_SECOND);
    }
}
