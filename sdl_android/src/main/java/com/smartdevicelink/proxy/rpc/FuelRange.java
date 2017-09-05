package com.smartdevicelink.proxy.rpc;

import com.smartdevicelink.proxy.RPCStruct;
import com.smartdevicelink.proxy.rpc.enums.FuelType;
import com.smartdevicelink.util.SdlDataTypeConverter;

import java.util.Hashtable;

public class FuelRange extends RPCStruct{
    public static final String KEY_TYPE = "type";
    public static final String KEY_RANGE = "range";

    public FuelRange() { }
    public FuelRange(Hashtable<String, Object> hash) {
        super(hash);
    }

    public void setType(FuelType fuelType) {
        if (fuelType != null) {
            store.put(KEY_TYPE, fuelType);
        } else {
            store.remove(KEY_TYPE);
        }
    }
    public FuelType getType() {
        Object obj = store.get(KEY_TYPE);
        if (obj instanceof FuelType) {
            return (FuelType) obj;
        } else if (obj instanceof String) {
            return FuelType.valueForString((String) obj);
        }
        return null;
    }

    public Float getRange() {
        Object value = store.get(KEY_RANGE);
        return SdlDataTypeConverter.objectToFloat(value);
    }

    public void setRange(Float range) {
        if (range != null) {
            store.put(KEY_RANGE, range);
        } else {
            store.remove(KEY_RANGE);
        }
    }
}
