package com.smartdevicelink.proxy.rpc;

import com.smartdevicelink.proxy.RPCStruct;
import com.smartdevicelink.util.DebugTool;
import com.smartdevicelink.util.SdlDataTypeConverter;

import java.util.Hashtable;

public class BrakePedalPositionType extends RPCStruct {
    public static final String KEY_VALUE = "value";
    public static final String KEY_TIMESTAMP = "timestamp";

    public BrakePedalPositionType() { }

    public BrakePedalPositionType(Hashtable<String, Object> hash) {
        super(hash);
    }

    public Float getValue() {
        Object value = store.get(KEY_VALUE);
        return SdlDataTypeConverter.objectToFloat(value);
    }

    public void setValue(Float value) {
        if (value != null) {
            store.put(KEY_VALUE, value);
        } else {
            store.remove(KEY_VALUE);
        }
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
