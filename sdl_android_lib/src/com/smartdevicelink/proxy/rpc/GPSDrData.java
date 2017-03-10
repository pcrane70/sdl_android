package com.smartdevicelink.proxy.rpc;

import com.smartdevicelink.proxy.RPCStruct;
import com.smartdevicelink.util.DebugTool;
import com.smartdevicelink.util.SdlDataTypeConverter;

import java.util.Hashtable;

public class GPSDrData extends RPCStruct {
    public static final String KEY_LONGITUDE_DEGREES = "longitudeDegrees";
    public static final String KEY_LATITUDE_DEGREES = "latitudeDegrees";
    public static final String KEY_ALTITUDE = "altitude";
    public static final String KEY_HEADING = "heading";
    public static final String KEY_SPEED = "speed";

    public GPSDrData() { }

    public GPSDrData(Hashtable<String, Object> hash) {
        super(hash);
    }

    public Float getLongitudeDegrees() {
        Object value = store.get(KEY_LONGITUDE_DEGREES);
        return SdlDataTypeConverter.objectToFloat(value);
    }

    public void setLongitudeDegrees(Float longitudeDegrees) {
        if (longitudeDegrees != null) {
            store.put(KEY_LONGITUDE_DEGREES, longitudeDegrees);
        } else {
            store.remove(KEY_LONGITUDE_DEGREES);
        }
    }

    public Float getLatitudeDegrees() {
        Object value = store.get(KEY_LATITUDE_DEGREES);
        return SdlDataTypeConverter.objectToFloat(value);
    }

    public void setLatitudeDegrees(Float latitudeDegrees) {
        if (latitudeDegrees != null) {
            store.put(KEY_LATITUDE_DEGREES, latitudeDegrees);
        } else {
            store.remove(KEY_LATITUDE_DEGREES);
        }
    }

    public Float getAltitude() {
        Object value = store.get(KEY_ALTITUDE);
        return SdlDataTypeConverter.objectToFloat(value);
    }

    public void setAltitude(Float altitude) {
        if (altitude != null) {
            store.put(KEY_ALTITUDE, altitude);
        } else {
            store.remove(KEY_ALTITUDE);
        }
    }

    public Float getHeading() {
        Object value = store.get(KEY_HEADING);
        return SdlDataTypeConverter.objectToFloat(value);
    }

    public void setHeading(Float heading) {
        if (heading != null) {
            store.put(KEY_HEADING, heading);
        } else {
            store.remove(KEY_HEADING);
        }
    }

    public Float getSpeed() {
        Object value = store.get(KEY_SPEED);
        return SdlDataTypeConverter.objectToFloat(value);
    }

    public void setSpeed(Float speed) {
        if (speed != null) {
            store.put(KEY_SPEED, speed);
        } else {
            store.remove(KEY_SPEED);
        }
    }
}
