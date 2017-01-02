package com.smartdevicelink.proxy.rpc;

import com.smartdevicelink.proxy.RPCStruct;
import com.smartdevicelink.proxy.rpc.enums.ComponentVolumeStatus;
import com.smartdevicelink.util.DebugTool;

import java.util.Hashtable;

public class ComponentVolumeStatusType extends RPCStruct {
    public static final String KEY_VALUE = "value";
    public static final String KEY_TIMESTAMP = "timestamp";

    public ComponentVolumeStatusType() { }

    public ComponentVolumeStatusType(Hashtable<String, Object> hash) {
        super(hash);
    }

    public void setValue(ComponentVolumeStatus value) {
        if (value != null) {
            store.put(KEY_VALUE, value);
        } else {
            store.remove(KEY_VALUE);
        }
    }

    public ComponentVolumeStatus getValue() {
        Object obj = store.get(KEY_VALUE);
        if (obj instanceof ComponentVolumeStatus) {
            return (ComponentVolumeStatus) obj;
        } else if (obj instanceof String) {
            ComponentVolumeStatus theCode = null;
            try {
                theCode = ComponentVolumeStatus.valueForString((String) obj);
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
