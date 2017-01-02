package com.smartdevicelink.proxy.rpc;

import com.smartdevicelink.proxy.RPCStruct;

import java.util.Hashtable;

public class WheelSpeedsData extends RPCStruct {
    public static final String KEY_WHLROTATFR_NO_CNT = "WhlRotatFr_No_Cnt";
    public static final String KEY_WHLROTATFL_NO_CNT = "WhlRotatFl_No_Cnt";
    public static final String KEY_WHLROTATRR_NO_CNT = "WhlRotatRr_No_Cnt";
    public static final String KEY_WHLROTATRL_NO_CNT = "WhlRotatRl_No_Cnt";
    public static final String KEY_WHLDIRFR_D_ACTL   = "WhlDirFr_D_Actl";
    public static final String KEY_WHLDIRFL_D_ACTL   = "WhlDirFl_D_Actl";
    public static final String KEY_WHLDIRRR_D_ACTL   = "WhlDirRr_D_Actl";
    public static final String KEY_WHLDIRRL_D_ACTL   = "WhlDirRl_D_Actl";

    public WheelSpeedsData() { }

    public WheelSpeedsData(Hashtable<String, Object> hash) {
        super(hash);
    }

    public void setWhlRotatFr_No_Cnt(Integer whlRotatFr_no_cnt) {
        if (whlRotatFr_no_cnt != null) {
            store.put(KEY_WHLROTATFR_NO_CNT, whlRotatFr_no_cnt);
        } else {
            store.remove(KEY_WHLROTATFR_NO_CNT);
        }
    }

    public Integer getWhlRotatFr_No_Cnt() {
        return (Integer) store.get(KEY_WHLROTATFR_NO_CNT);
    }

    public void setWhlRotatFl_No_Cnt(Integer whlRotatFl_no_cnt) {
        if (whlRotatFl_no_cnt != null) {
            store.put(KEY_WHLROTATFL_NO_CNT, whlRotatFl_no_cnt);
        } else {
            store.remove(KEY_WHLROTATFL_NO_CNT);
        }
    }

    public Integer getWhlRotatFl_No_Cnt() {
        return (Integer) store.get(KEY_WHLROTATFL_NO_CNT);
    }

    public void setWhlRotatRr_No_Cnt(Integer whlRotatRr_no_cnt) {
        if (whlRotatRr_no_cnt != null) {
            store.put(KEY_WHLROTATRR_NO_CNT, whlRotatRr_no_cnt);
        } else {
            store.remove(KEY_WHLROTATRR_NO_CNT);
        }
    }

    public Integer getWhlRotatRr_No_Cnt() {
        return (Integer) store.get(KEY_WHLROTATRR_NO_CNT);
    }

    public void setWhlRotatRl_No_Cnt(Integer whlRotatRl_no_cnt) {
        if (whlRotatRl_no_cnt != null) {
            store.put(KEY_WHLROTATRL_NO_CNT, whlRotatRl_no_cnt);
        } else {
            store.remove(KEY_WHLROTATRL_NO_CNT);
        }
    }

    public Integer getWhlRotatRl_No_Cnt() {
        return (Integer) store.get(KEY_WHLROTATRL_NO_CNT);
    }

    public void setWhlDirFr_D_Actl(Integer whlDirFr_d_actl) {
        if (whlDirFr_d_actl != null) {
            store.put(KEY_WHLDIRFR_D_ACTL, whlDirFr_d_actl);
        } else {
            store.remove(KEY_WHLDIRFR_D_ACTL);
        }
    }

    public Integer getWhlDirFr_D_Actl() {
        return (Integer) store.get(KEY_WHLDIRFR_D_ACTL);
    }

    public void setWhlDirFl_D_Actl(Integer whlDirFl_d_actl) {
        if (whlDirFl_d_actl != null) {
            store.put(KEY_WHLDIRFL_D_ACTL, whlDirFl_d_actl);
        } else {
            store.remove(KEY_WHLDIRFL_D_ACTL);
        }
    }

    public Integer getWhlDirFl_D_Actl() {
        return (Integer) store.get(KEY_WHLDIRFL_D_ACTL);
    }

    public void setWhlDirRr_D_Actl(Integer whlDirRr_d_actl) {
        if (whlDirRr_d_actl != null) {
            store.put(KEY_WHLDIRRR_D_ACTL, whlDirRr_d_actl);
        } else {
            store.remove(KEY_WHLDIRRR_D_ACTL);
        }
    }

    public Integer getWhlDirRr_D_Actl() {
        return (Integer) store.get(KEY_WHLDIRRR_D_ACTL);
    }

    public void setWhlDirRl_D_Actl(Integer whlDirRl_d_actl) {
        if (whlDirRl_d_actl != null) {
            store.put(KEY_WHLDIRRL_D_ACTL, whlDirRl_d_actl);
        } else {
            store.remove(KEY_WHLDIRRL_D_ACTL);
        }
    }

    public Integer getWhlDirRl_D_Actl() {
        return (Integer) store.get(KEY_WHLDIRRL_D_ACTL);
    }
}
