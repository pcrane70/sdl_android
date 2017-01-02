package com.smartdevicelink.proxy.rpc;

import com.smartdevicelink.proxy.RPCStruct;

import java.util.Hashtable;

public class AxisSensorData extends RPCStruct {
    public static final String KEY_CUMULATIVE_VALUE0 = "cumulativeValue0";
    public static final String KEY_CUMULATIVE_VALUE1 = "cumulativeValue1";
    public static final String KEY_CUMULATIVE_VALUE2 = "cumulativeValue2";

    public AxisSensorData() { }

    public AxisSensorData(Hashtable<String, Object> hash) {
        super(hash);
    }

    public void setCumulativeValue0(Integer cumulativeValue0) {
        if (cumulativeValue0 != null) {
            store.put(KEY_CUMULATIVE_VALUE0, cumulativeValue0);
        } else {
            store.remove(KEY_CUMULATIVE_VALUE0);
        }
    }

    public Integer getCumulativeValue0() {
        return (Integer) store.get(KEY_CUMULATIVE_VALUE0);
    }

    public void setCumulativeValue1(Integer cumulativeValue1) {
        if (cumulativeValue1 != null) {
            store.put(KEY_CUMULATIVE_VALUE1, cumulativeValue1);
        } else {
            store.remove(KEY_CUMULATIVE_VALUE1);
        }
    }

    public Integer getCumulativeValue1() {
        return (Integer) store.get(KEY_CUMULATIVE_VALUE1);
    }

    public void setCumulativeValue2(Integer cumulativeValue2) {
        if (cumulativeValue2 != null) {
            store.put(KEY_CUMULATIVE_VALUE2, cumulativeValue2);
        } else {
            store.remove(KEY_CUMULATIVE_VALUE2);
        }
    }

    public Integer getCumulativeValue2() {
        return (Integer) store.get(KEY_CUMULATIVE_VALUE2);
    }
}
