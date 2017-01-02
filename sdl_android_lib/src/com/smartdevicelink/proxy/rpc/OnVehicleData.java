package com.smartdevicelink.proxy.rpc;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCNotification;
import com.smartdevicelink.proxy.rpc.enums.ComponentVolumeStatus;
import com.smartdevicelink.proxy.rpc.enums.PRNDL;
import com.smartdevicelink.proxy.rpc.enums.VehicleDataEventStatus;
import com.smartdevicelink.proxy.rpc.enums.WiperStatus;
import com.smartdevicelink.util.DebugTool;
import com.smartdevicelink.util.SdlDataTypeConverter;
/**
 *Individual requested DID result and data.
 *
 *  
 * <p>Callback for the periodic and non periodic vehicle data read function.</p>
 * 
 * <p> <b>Note:</b></p>
 * 
 * Initially SDL sends SubscribeVehicleData for getting the periodic updates from HMI whenever each of subscribed data types changes. OnVehicleData is expected to bring such updated values to SDL
 * 
 *
 * 
 * 
 * <p><b>Parameter List</b></p>
 * <table border="1" rules="all">
 * 		<tr>
 * 			<th>Param Name</th>
 * 			<th>Type</th>
 * 			<th>Description</th>
 *                 <th> Req.</th>
 * 			<th>Notes</th>
 * 			<th>Version Available</th>
 * 		</tr>
 * 		<tr>
 * 			<td>Gps</td>
 * 			<td>Boolean</td>
 * 			<td>GPS data. See {@linkplain GPSdata} for details</td>
 *                 <td>N</td>
 * 			<td>Subscribable </td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>Speed</td>
 * 			<td>Float</td>
 * 			<td>The vehicle speed in kilometers per hour</td>
 *                 <td>N</td>
 * 			<td>Subscribable</td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>rpm</td>
 * 			<td>Integer</td>
 * 			<td>The number of revolutions per minute of the engine</td>
 *                 <td>N</td>
 * 			<td>Subscribable</td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>fuelLevel</td>
 * 			<td>Float</td>
 * 			<td>The fuel level in the tank (percentage)</td>
 *                 <td>N</td>
 * 			<td>Subscribable</td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>fuelLevel_State</td>
 * 			<td>ComponentVolumeStatus</td>
 * 			<td>The fuel level state (Ok/Low)</td>
 *                 <td>N</td>
 * 			<td>Subscribable</td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>instantFuelConsumption</td>
 * 			<td>Float</td>
 * 			<td>The instantaneous fuel consumption in microlitres</td>
 *                 <td>N</td>
 * 			<td>Subscribable </td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>externalTemperature</td>
 * 			<td>Float</td>
 * 			<td>The external temperature in degrees celsius.</td>
 *                 <td>N</td>
 * 			<td>Subscribable </td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>vin</td>
 * 			<td>String</td>
 * 			<td>Vehicle identification number.</td>
 *                 <td>N</td>
 * 			<td>Subscribable </td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>prndl</td>
 * 			<td>PRNDL</td>
 * 			<td>Currently selected gear.</td>
 *                 <td>N</td>
 * 			<td>Subscribable </td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>tirePressure</td>
 * 			<td>TireStatus</td>
 * 			<td>Tire pressure status</td>
 *                 <td>N</td>
 * 			<td>Subscribable</td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>odometer</td>
 * 			<td>Integer</td>
 * 			<td>Odometer in km</td>
 *                 <td>N</td>
 * 			<td>Subscribable </td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>beltStatus</td>
 * 			<td>BeltStatus</td>
 * 			<td>The status of the seat belts.</td>
 *                 <td>N</td>
 * 			<td>Subscribable </td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>bodyInformation</td>
 * 			<td>BodyInformation</td>
 * 			<td>The body information including power modes.</td>
 *                 <td>N</td>
 * 			<td>Subscribable</td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>deviceStatus</td>
 * 			<td>DeviceStatus</td>
 * 			<td>The connected mobile device status including signal and battery strength.</td>
 *                 <td>N</td>
 * 			<td>Subscribable</td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>eCallInfo</td>
 * 			<td>ECallInfo</td>
 * 			<td>Emergency Call notification and confirmation data.</td>
 *                 <td>N</td>
 * 			<td>Subscribable</td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>airbagStatus</td>
 * 			<td>AirBagStatus</td>
 * 			<td>The status of the air bags.</td>
 *                 <td>N</td>
 * 			<td>Subscribable</td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>emergencyEvent</td>
 * 			<td>EmergencyEvernt</td>
 * 			<td>Information related to an emergency event (and if it occurred).</td>
 *                 <td>N</td>
 * 			<td>Subscribable</td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>clusterModeStatus</td>
 * 			<td>ClusterModeStatus</td>
 * 			<td>The status modes of the instrument panel cluster.</td>
 *                 <td>N</td>
 * 			<td>Subscribable</td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>myKey</td>
 * 			<td>MyKey</td>
 * 			<td>Information related to the MyKey feature.</td>
 *                 <td>N</td>
 * 			<td>Subscribable</td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 *
 * 		<tr>
 * 			<td>driverBraking</td>
 * 			<td>vehicleDataEventStatus</td>
 * 			<td>The status of the brake pedal.</td>
 *                 <td>N</td>
 * 			<td>Subscribable</td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>wiperStatus</td>
 * 			<td>WiperStatus</td>
 * 			<td>The status of the wipers</td>
 *                 <td>N</td>
 * 			<td> </td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>headLampStatus</td>
 * 			<td>headLampStatus</td>
 * 			<td>Status of the head lamps</td>
 *                 <td>N</td>
 * 			<td></td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>engineTorque</td>
 * 			<td>Float</td>
 * 			<td>Torque value for engine (in Nm) on non-diesel variants</td>
 *                 <td>N</td>
 * 			<td>minvalue:-1000; maxvalue:2000</td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>accPedalPosition</td>
 * 			<td>Float</td>
 * 			<td>Accelerator pedal position (percentage depressed)</td>
 *                 <td>N</td>
 * 			<td>minvalue: 0; maxvalue:100</td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 * 		<tr>
 * 			<td>steeringWheelAngle</td>
 * 			<td>Float</td>
 * 			<td>Current angle of the steering wheel (in deg)</td>
 *                 <td>N</td>
 * 			<td> minvalue: -2000; maxvalue:2000</td>
 * 			<td>SmartDeviceLink 2.0</td>
 * 		</tr>
 *  </table>
 *
 * @since SmartDeviceLink 1.0
 * 
 * @see SubscribeVehicleData
 * @see UnsubscribeVehicleData
 *
 *
 */
public class OnVehicleData extends RPCNotification {
	public static final String KEY_SPEED = "speed";
    public static final String KEY_SPEED_ARRAY = "speedArray";
	public static final String KEY_RPM = "rpm";
    public static final String KEY_RPM_ARRAY = "rpmArray";
	public static final String KEY_EXTERNAL_TEMPERATURE = "externalTemperature";
    public static final String KEY_EXTERNAL_TEMPERATURE_ARRAY = "externalTemperatureArray";
	public static final String KEY_FUEL_LEVEL = "fuelLevel";
    public static final String KEY_FUEL_LEVEL_ARRAY = "fuelLevelArray";
	public static final String KEY_VIN = "vin";
	public static final String KEY_PRNDL = "prndl";
    public static final String KEY_PRNDL_ARRAY = "prndlArray";
	public static final String KEY_TIRE_PRESSURE = "tirePressure";
    public static final String KEY_TIRE_PRESSURE_ARRAY = "tirePressureArray";
	public static final String KEY_ENGINE_TORQUE = "engineTorque";
    public static final String KEY_ENGINE_TORQUE_ARRAY = "engineTorqueArray";
	public static final String KEY_ODOMETER = "odometer";
	public static final String KEY_GPS = "gps";
    public static final String KEY_GPS_ARRAY = "gpsArray";
	public static final String KEY_FUEL_LEVEL_STATE = "fuelLevel_State";
    public static final String KEY_FUEL_LEVEL_STATE_ARRAY = "fuelLevel_StateArray";
	public static final String KEY_INSTANT_FUEL_CONSUMPTION = "instantFuelConsumption";
	public static final String KEY_BELT_STATUS = "beltStatus";
	public static final String KEY_BODY_INFORMATION = "bodyInformation";
	public static final String KEY_DEVICE_STATUS = "deviceStatus";
	public static final String KEY_DRIVER_BRAKING = "driverBraking";
    public static final String KEY_DRIVER_BRAKING_ARRAY = "driverBrakingArray";
	public static final String KEY_WIPER_STATUS = "wiperStatus";
	public static final String KEY_HEAD_LAMP_STATUS = "headLampStatus";
	public static final String KEY_ACC_PEDAL_POSITION = "accPedalPosition";
    public static final String KEY_ACC_PEDAL_POSITION_ARRAY = "accPedalPositionArray";
	public static final String KEY_STEERING_WHEEL_ANGLE = "steeringWheelAngle";
    public static final String KEY_STEERING_WHEEL_ANGLE_ARRAY = "steeringWheelAngleArray";
	public static final String KEY_E_CALL_INFO = "eCallInfo";
	public static final String KEY_AIRBAG_STATUS = "airbagStatus";
	public static final String KEY_EMERGENCY_EVENT = "emergencyEvent";
	public static final String KEY_CLUSTER_MODE_STATUS = "clusterModeStatus";
	public static final String KEY_MY_KEY = "myKey";
    public static final String KEY_ACCELEROMETER_ARRAY = "accelerometerArray";
    public static final String KEY_GYROSCOPE_ARRAY = "gyroscopeArray";
    public static final String KEY_WHEEL_SPEEDS_ARRAY = "wheelSpeedsArray";

    public OnVehicleData() {
        super(FunctionID.ON_VEHICLE_DATA.toString());
    }
    public OnVehicleData(Hashtable<String, Object> hash) {
        super(hash);
    }

    public void setGps(GPSData gps) {
    	if (gps != null) {
    		parameters.put(KEY_GPS, gps);
    	} else {
    		parameters.remove(KEY_GPS);
    	}
    }
    @SuppressWarnings("unchecked")
    public GPSData getGps() {
    	Object obj = parameters.get(KEY_GPS);
        if (obj instanceof GPSData) {
            return (GPSData) obj;
        } else if (obj instanceof Hashtable) {
        	GPSData theCode = null;
            try {
                theCode = new GPSData((Hashtable<String, Object>) obj);
            } catch (Exception e) {
            	DebugTool.logError("Failed to parse " + getClass().getSimpleName() + "." + KEY_GPS, e);
            }
            return theCode;
        }
        return null;
    }


    public void setGpsArray(List<GPSDataType> gpsArray){
        if(gpsArray != null){
            parameters.put(KEY_GPS_ARRAY, gpsArray);
        }
        else{
            parameters.remove(KEY_GPS_ARRAY);
        }
    }

    @SuppressWarnings("unchecked")
    public List<GPSDataType> getGpsArray(){
        if(parameters.get(KEY_GPS_ARRAY) instanceof List<?>){
            List<?> list = (List<?>) parameters.get(KEY_GPS_ARRAY);
            if(list != null && list.size() > 0){
                Object obj = list.get(0);
                if(obj instanceof GPSDataType){
                    return (List<GPSDataType>) list;
                }
                else if(obj instanceof Hashtable){
                    List<GPSDataType> newList = new ArrayList<GPSDataType>();
                    for(Object hashObj : list){
                        newList.add(new GPSDataType((Hashtable<String, Object>) hashObj));
                    }
                    return newList;
                }
            }
        }
        return null;
    }

    public void setSpeed(Double speed) {
    	if (speed != null) {
    		parameters.put(KEY_SPEED, speed);
    	} else {
    		parameters.remove(KEY_SPEED);
    	}
    }
    public Double getSpeed() {
    	Object object = parameters.get(KEY_SPEED);
    	return SdlDataTypeConverter.objectToDouble(object);
    }

    public void setSpeedArray(List<SpeedType> speedArray){
        if(speedArray != null){
            parameters.put(KEY_SPEED_ARRAY, speedArray);
        }
        else{
            parameters.remove(KEY_SPEED_ARRAY);
        }
    }

    @SuppressWarnings("unchecked")
    public List<SpeedType> getSpeedArray(){
        if(parameters.get(KEY_SPEED_ARRAY) instanceof List<?>){
            List<?> list = (List<?>) parameters.get(KEY_SPEED_ARRAY);
            if(list != null && list.size() > 0){
                Object obj = list.get(0);
                if(obj instanceof SpeedType){
                    return (List<SpeedType>) list;
                }
                else if(obj instanceof Hashtable){
                    List<SpeedType> newList = new ArrayList<SpeedType>();
                    for(Object hashObj : list){
                        newList.add(new SpeedType((Hashtable<String, Object>) hashObj));
                    }
                    return newList;
                }
            }
        }
        return null;
    }

    public void setRpm(Integer rpm) {
    	if (rpm != null) {
    		parameters.put(KEY_RPM, rpm);
    	} else {
    		parameters.remove(KEY_RPM);
    	}
    }
    public Integer getRpm() {
    	return (Integer) parameters.get(KEY_RPM);
    }

    public void setRpmArray(List<RpmType> rpmArray){
        if(rpmArray != null){
            parameters.put(KEY_RPM_ARRAY, rpmArray);
        }
        else{
            parameters.remove(KEY_RPM_ARRAY);
        }
    }

    @SuppressWarnings("unchecked")
    public List<RpmType> getRpmArray(){
        if(parameters.get(KEY_RPM_ARRAY) instanceof List<?>){
            List<?> list = (List<?>) parameters.get(KEY_RPM_ARRAY);
            if(list != null && list.size() > 0){
                Object obj = list.get(0);
                if(obj instanceof RpmType){
                    return (List<RpmType>) list;
                }
                else if(obj instanceof Hashtable){
                    List<RpmType> newList = new ArrayList<RpmType>();
                    for(Object hashObj : list){
                        newList.add(new RpmType((Hashtable<String, Object>) hashObj));
                    }
                    return newList;
                }
            }
        }
        return null;
    }

    public void setFuelLevel(Double fuelLevel) {
    	if (fuelLevel != null) {
    		parameters.put(KEY_FUEL_LEVEL, fuelLevel);
    	} else {
    		parameters.remove(KEY_FUEL_LEVEL);
    	}
    }
    public Double getFuelLevel() {
    	Object object = parameters.get(KEY_FUEL_LEVEL);
    	return SdlDataTypeConverter.objectToDouble(object);
    }

    public void setFuelLevelArray(List<FuelLevelType> fuelLevelArray){
        if(fuelLevelArray != null){
            parameters.put(KEY_FUEL_LEVEL_ARRAY, fuelLevelArray);
        }
        else{
            parameters.remove(KEY_FUEL_LEVEL_ARRAY);
        }
    }

    @SuppressWarnings("unchecked")
    public List<FuelLevelType> getFuelLevelArray(){
        if(parameters.get(KEY_FUEL_LEVEL_ARRAY) instanceof List<?>){
            List<?> list = (List<?>) parameters.get(KEY_FUEL_LEVEL_ARRAY);
            if(list != null && list.size() > 0){
                Object obj = list.get(0);
                if(obj instanceof FuelLevelType){
                    return (List<FuelLevelType>) list;
                }
                else if(obj instanceof Hashtable){
                    List<FuelLevelType> newList = new ArrayList<FuelLevelType>();
                    for(Object hashObj : list){
                        newList.add(new FuelLevelType((Hashtable<String, Object>) hashObj));
                    }
                    return newList;
                }
            }
        }
        return null;
    }

    @Deprecated
    public void setFuelLevel_State(ComponentVolumeStatus fuelLevel_State) {
        setFuelLevelState(fuelLevel_State);
    }
    @Deprecated
    public ComponentVolumeStatus getFuelLevel_State() {
        return getFuelLevelState();
    }
    public void setFuelLevelState(ComponentVolumeStatus fuelLevelState) {
        if (fuelLevelState != null) {
            parameters.put(KEY_FUEL_LEVEL_STATE, fuelLevelState);
        } else {
            parameters.remove(KEY_FUEL_LEVEL_STATE);
        }
    }
    public ComponentVolumeStatus getFuelLevelState() {
        Object obj = parameters.get(KEY_FUEL_LEVEL_STATE);
        if (obj instanceof ComponentVolumeStatus) {
            return (ComponentVolumeStatus) obj;
        } else if (obj instanceof String) {
            ComponentVolumeStatus theCode = null;
            try {
                theCode = ComponentVolumeStatus.valueForString((String) obj);
            } catch (Exception e) {
                DebugTool.logError("Failed to parse " + getClass().getSimpleName() + "." + KEY_FUEL_LEVEL_STATE, e);
            }
            return theCode;
        }
        return null;
    }

    public void setFuelLevelStateArray(List<ComponentVolumeStatusType> fuelLevelStateArray){
        if(fuelLevelStateArray != null){
            parameters.put(KEY_FUEL_LEVEL_STATE_ARRAY, fuelLevelStateArray);
        }
        else{
            parameters.remove(KEY_FUEL_LEVEL_STATE_ARRAY);
        }
    }

    @SuppressWarnings("unchecked")
    public List<ComponentVolumeStatusType> getFuelLevelStateArray(){
        if(parameters.get(KEY_FUEL_LEVEL_STATE_ARRAY) instanceof List<?>){
            List<?> list = (List<?>) parameters.get(KEY_FUEL_LEVEL_STATE_ARRAY);
            if(list != null && list.size() > 0){
                Object obj = list.get(0);
                if(obj instanceof ComponentVolumeStatusType){
                    return (List<ComponentVolumeStatusType>) list;
                }
                else if(obj instanceof Hashtable){
                    List<ComponentVolumeStatusType> newList = new ArrayList<ComponentVolumeStatusType>();
                    for(Object hashObj : list){
                        newList.add(new ComponentVolumeStatusType((Hashtable<String, Object>) hashObj));
                    }
                    return newList;
                }
            }
        }
        return null;
    }

    public void setInstantFuelConsumption(Double instantFuelConsumption) {
    	if (instantFuelConsumption != null) {
    		parameters.put(KEY_INSTANT_FUEL_CONSUMPTION, instantFuelConsumption);
    	} else {
    		parameters.remove(KEY_INSTANT_FUEL_CONSUMPTION);
    	}
    }
    public Double getInstantFuelConsumption() {
    	Object object = parameters.get(KEY_INSTANT_FUEL_CONSUMPTION);
    	return SdlDataTypeConverter.objectToDouble(object);
    }
    public void setExternalTemperature(Double externalTemperature) {
    	if (externalTemperature != null) {
    		parameters.put(KEY_EXTERNAL_TEMPERATURE, externalTemperature);
    	} else {
    		parameters.remove(KEY_EXTERNAL_TEMPERATURE);
    	}
    }
    public Double getExternalTemperature() {
    	Object object = parameters.get(KEY_EXTERNAL_TEMPERATURE);
    	return SdlDataTypeConverter.objectToDouble(object);
    }

    public void setExternalTemperatureArray(List<ExternalTemperatureType> externalTemperatureArray){
        if(externalTemperatureArray != null){
            parameters.put(KEY_EXTERNAL_TEMPERATURE_ARRAY, externalTemperatureArray);
        }
        else{
            parameters.remove(KEY_EXTERNAL_TEMPERATURE_ARRAY);
        }
    }

    @SuppressWarnings("unchecked")
    public List<ExternalTemperatureType> getExternalTemperatureArray(){
        if(parameters.get(KEY_EXTERNAL_TEMPERATURE_ARRAY) instanceof List<?>){
            List<?> list = (List<?>) parameters.get(KEY_EXTERNAL_TEMPERATURE_ARRAY);
            if(list != null && list.size() > 0){
                Object obj = list.get(0);
                if(obj instanceof ExternalTemperatureType){
                    return (List<ExternalTemperatureType>) list;
                }
                else if(obj instanceof Hashtable){
                    List<ExternalTemperatureType> newList = new ArrayList<ExternalTemperatureType>();
                    for(Object hashObj : list){
                        newList.add(new ExternalTemperatureType((Hashtable<String, Object>) hashObj));
                    }
                    return newList;
                }
            }
        }
        return null;
    }

    public void setVin(String vin) {
    	if (vin != null) {
    		parameters.put(KEY_VIN, vin);
    	} else {
    		parameters.remove(KEY_VIN);
    	}
    }
    public String getVin() {
    	return (String) parameters.get(KEY_VIN);
    }
    public void setPrndl(PRNDL prndl) {
    	if (prndl != null) {
    		parameters.put(KEY_PRNDL, prndl);
    	} else {
    		parameters.remove(KEY_PRNDL);
    	}
    }
    public PRNDL getPrndl() {
        Object obj = parameters.get(KEY_PRNDL);
        if (obj instanceof PRNDL) {
            return (PRNDL) obj;
        } else if (obj instanceof String) {
        	return PRNDL.valueForString((String) obj);
        }
        return null;
    }

    public void setPrndlArray(List<PRNDLType> prndlArray){
        if(prndlArray != null){
            parameters.put(KEY_PRNDL_ARRAY, prndlArray);
        }
        else{
            parameters.remove(KEY_PRNDL_ARRAY);
        }
    }

    @SuppressWarnings("unchecked")
    public List<PRNDLType> getPrndlArray(){
        if(parameters.get(KEY_PRNDL_ARRAY) instanceof List<?>){
            List<?> list = (List<?>) parameters.get(KEY_PRNDL_ARRAY);
            if(list != null && list.size() > 0){
                Object obj = list.get(0);
                if(obj instanceof PRNDLType){
                    return (List<PRNDLType>) list;
                }
                else if(obj instanceof Hashtable){
                    List<PRNDLType> newList = new ArrayList<PRNDLType>();
                    for(Object hashObj : list){
                        newList.add(new PRNDLType((Hashtable<String, Object>) hashObj));
                    }
                    return newList;
                }
            }
        }
        return null;
    }

    public void setTirePressure(TireStatus tirePressure) {
    	if (tirePressure != null) {
    		parameters.put(KEY_TIRE_PRESSURE, tirePressure);
    	} else {
    		parameters.remove(KEY_TIRE_PRESSURE);
    	}
    }
    @SuppressWarnings("unchecked")
    public TireStatus getTirePressure() {
    	Object obj = parameters.get(KEY_TIRE_PRESSURE);
        if (obj instanceof TireStatus) {
            return (TireStatus) obj;
        } else if (obj instanceof Hashtable) {
        	try {
        		return new TireStatus((Hashtable<String, Object>) obj);
            } catch (Exception e) {
            	DebugTool.logError("Failed to parse " + getClass().getSimpleName() + "." + KEY_TIRE_PRESSURE, e);
            }
        }
        return null;
    }

    public void setTirePressureArray(List<TireStatusType> tirePressureArray){
        if(tirePressureArray != null){
            parameters.put(KEY_TIRE_PRESSURE_ARRAY, tirePressureArray);
        }
        else{
            parameters.remove(KEY_TIRE_PRESSURE_ARRAY);
        }
    }

    @SuppressWarnings("unchecked")
    public List<TireStatusType> getTirePressureArray(){
        if(parameters.get(KEY_TIRE_PRESSURE_ARRAY) instanceof List<?>){
            List<?> list = (List<?>) parameters.get(KEY_TIRE_PRESSURE_ARRAY);
            if(list != null && list.size() > 0){
                Object obj = list.get(0);
                if(obj instanceof TireStatusType){
                    return (List<TireStatusType>) list;
                }
                else if(obj instanceof Hashtable){
                    List<TireStatusType> newList = new ArrayList<TireStatusType>();
                    for(Object hashObj : list){
                        newList.add(new TireStatusType((Hashtable<String, Object>) hashObj));
                    }
                    return newList;
                }
            }
        }
        return null;
    }

    public void setOdometer(Integer odometer) {
    	if (odometer != null) {
    		parameters.put(KEY_ODOMETER, odometer);
    	} else {
    		parameters.remove(KEY_ODOMETER);
    	}
    }
    public Integer getOdometer() {
    	return (Integer) parameters.get(KEY_ODOMETER);
    }
    public void setBeltStatus(BeltStatus beltStatus) {
        if (beltStatus != null) {
            parameters.put(KEY_BELT_STATUS, beltStatus);
        } else {
        	parameters.remove(KEY_BELT_STATUS);
        }
    }
    @SuppressWarnings("unchecked")
    public BeltStatus getBeltStatus() {
    	Object obj = parameters.get(KEY_BELT_STATUS);
        if (obj instanceof BeltStatus) {
            return (BeltStatus) obj;
        } else if (obj instanceof Hashtable) {
        	try {
        		return new BeltStatus((Hashtable<String, Object>) obj);
            } catch (Exception e) {
            	DebugTool.logError("Failed to parse " + getClass().getSimpleName() + "." + KEY_BELT_STATUS, e);
            }
        }
        return null;
    }
    public void setBodyInformation(BodyInformation bodyInformation) {
        if (bodyInformation != null) {
            parameters.put(KEY_BODY_INFORMATION, bodyInformation);
        } else {
        	parameters.remove(KEY_BODY_INFORMATION);
        }
    }
    @SuppressWarnings("unchecked")
    public BodyInformation getBodyInformation() {
    	Object obj = parameters.get(KEY_BODY_INFORMATION);
        if (obj instanceof BodyInformation) {
            return (BodyInformation) obj;
        } else if (obj instanceof Hashtable) {
        	try {
        		return new BodyInformation((Hashtable<String, Object>) obj);
            } catch (Exception e) {
            	DebugTool.logError("Failed to parse " + getClass().getSimpleName() + "." + KEY_BODY_INFORMATION, e);
            }
        }
        return null;
    }
    public void setDeviceStatus(DeviceStatus deviceStatus) {
        if (deviceStatus != null) {
            parameters.put(KEY_DEVICE_STATUS, deviceStatus);
        } else {
        	parameters.remove(KEY_DEVICE_STATUS);
        }
    }
    @SuppressWarnings("unchecked")
    public DeviceStatus getDeviceStatus() {
    	Object obj = parameters.get(KEY_DEVICE_STATUS);
        if (obj instanceof DeviceStatus) {
            return (DeviceStatus) obj;
        } else if (obj instanceof Hashtable) {
        	try {
        		return new DeviceStatus((Hashtable<String, Object>) obj);
            } catch (Exception e) {
            	DebugTool.logError("Failed to parse " + getClass().getSimpleName() + "." + KEY_DEVICE_STATUS, e);
            }
        }
        return null;
    }
    public void setDriverBraking(VehicleDataEventStatus driverBraking) {
        if (driverBraking != null) {
            parameters.put(KEY_DRIVER_BRAKING, driverBraking);
        } else {
        	parameters.remove(KEY_DRIVER_BRAKING);
        }
    }
    public VehicleDataEventStatus getDriverBraking() {
        Object obj = parameters.get(KEY_DRIVER_BRAKING);
        if (obj instanceof VehicleDataEventStatus) {
            return (VehicleDataEventStatus) obj;
        } else if (obj instanceof String) {
        	return VehicleDataEventStatus.valueForString((String) obj);
        }
        return null;
    }

    public void setDriverBrakingArray(List<VehicleDataEventStatusType> driverBrakingArray){
        if(driverBrakingArray != null){
            parameters.put(KEY_DRIVER_BRAKING_ARRAY, driverBrakingArray);
        }
        else{
            parameters.remove(KEY_DRIVER_BRAKING_ARRAY);
        }
    }

    @SuppressWarnings("unchecked")
    public List<VehicleDataEventStatusType> getDriverBrakingArray(){
        if(parameters.get(KEY_DRIVER_BRAKING_ARRAY) instanceof List<?>){
            List<?> list = (List<?>) parameters.get(KEY_DRIVER_BRAKING_ARRAY);
            if(list != null && list.size() > 0){
                Object obj = list.get(0);
                if(obj instanceof VehicleDataEventStatusType){
                    return (List<VehicleDataEventStatusType>) list;
                }
                else if(obj instanceof Hashtable){
                    List<VehicleDataEventStatusType> newList = new ArrayList<VehicleDataEventStatusType>();
                    for(Object hashObj : list){
                        newList.add(new VehicleDataEventStatusType((Hashtable<String, Object>) hashObj));
                    }
                    return newList;
                }
            }
        }
        return null;
    }

    public void setWiperStatus(WiperStatus wiperStatus) {
        if (wiperStatus != null) {
            parameters.put(KEY_WIPER_STATUS, wiperStatus);
        } else {
        	parameters.remove(KEY_WIPER_STATUS);
        }
    }
    public WiperStatus getWiperStatus() {
        Object obj = parameters.get(KEY_WIPER_STATUS);
        if (obj instanceof WiperStatus) {
            return (WiperStatus) obj;
        } else if (obj instanceof String) {
        	return WiperStatus.valueForString((String) obj);
        }
        return null;
    }
    public void setHeadLampStatus(HeadLampStatus headLampStatus) {
        if (headLampStatus != null) {
            parameters.put(KEY_HEAD_LAMP_STATUS, headLampStatus);
        } else {
        	parameters.remove(KEY_HEAD_LAMP_STATUS);
        }
    }
    @SuppressWarnings("unchecked")
    public HeadLampStatus getHeadLampStatus() {
    	Object obj = parameters.get(KEY_HEAD_LAMP_STATUS);
        if (obj instanceof HeadLampStatus) {
            return (HeadLampStatus) obj;
        } else if (obj instanceof Hashtable) {
        	try {
        		return new HeadLampStatus((Hashtable<String, Object>) obj);
            } catch (Exception e) {
            	DebugTool.logError("Failed to parse " + getClass().getSimpleName() + "." + KEY_HEAD_LAMP_STATUS, e);
            }
        }
        return null;
    }
    public void setEngineTorque(Double engineTorque) {
        if (engineTorque != null) {
            parameters.put(KEY_ENGINE_TORQUE, engineTorque);
        } else {
        	parameters.remove(KEY_ENGINE_TORQUE);
        }
    }
    public Double getEngineTorque() {
    	Object object = parameters.get(KEY_ENGINE_TORQUE);
    	return SdlDataTypeConverter.objectToDouble(object);
    }

    public void setEngineTorqueArray(List<EngineTorqueType> engineTorqueArray){
        if(engineTorqueArray != null){
            parameters.put(KEY_ENGINE_TORQUE_ARRAY, engineTorqueArray);
        }
        else{
            parameters.remove(KEY_ENGINE_TORQUE_ARRAY);
        }
    }

    @SuppressWarnings("unchecked")
    public List<EngineTorqueType> getEngineTorqueArray(){
        if(parameters.get(KEY_ENGINE_TORQUE_ARRAY) instanceof List<?>){
            List<?> list = (List<?>) parameters.get(KEY_ENGINE_TORQUE_ARRAY);
            if(list != null && list.size() > 0){
                Object obj = list.get(0);
                if(obj instanceof EngineTorqueType){
                    return (List<EngineTorqueType>) list;
                }
                else if(obj instanceof Hashtable){
                    List<EngineTorqueType> newList = new ArrayList<EngineTorqueType>();
                    for(Object hashObj : list){
                        newList.add(new EngineTorqueType((Hashtable<String, Object>) hashObj));
                    }
                    return newList;
                }
            }
        }
        return null;
    }

    public void setAccPedalPosition(Double accPedalPosition) {
        if (accPedalPosition != null) {
            parameters.put(KEY_ACC_PEDAL_POSITION, accPedalPosition);
        } else {
        	parameters.remove(KEY_ACC_PEDAL_POSITION);
        }
    }
    public Double getAccPedalPosition() {
    	Object object = parameters.get(KEY_ACC_PEDAL_POSITION);
    	return SdlDataTypeConverter.objectToDouble(object);
    }

    public void setAccPedalPositionArray(List<AccPedalPositionType> accPedalPositionArray){
        if(accPedalPositionArray != null){
            parameters.put(KEY_ACC_PEDAL_POSITION_ARRAY, accPedalPositionArray);
        }
        else{
            parameters.remove(KEY_ACC_PEDAL_POSITION_ARRAY);
        }
    }

    @SuppressWarnings("unchecked")
    public List<AccPedalPositionType> getAccPedalPositionArray(){
        if(parameters.get(KEY_ACC_PEDAL_POSITION_ARRAY) instanceof List<?>){
            List<?> list = (List<?>) parameters.get(KEY_ACC_PEDAL_POSITION_ARRAY);
            if(list != null && list.size() > 0){
                Object obj = list.get(0);
                if(obj instanceof AccPedalPositionType){
                    return (List<AccPedalPositionType>) list;
                }
                else if(obj instanceof Hashtable){
                    List<AccPedalPositionType> newList = new ArrayList<AccPedalPositionType>();
                    for(Object hashObj : list){
                        newList.add(new AccPedalPositionType((Hashtable<String, Object>) hashObj));
                    }
                    return newList;
                }
            }
        }
        return null;
    }

    public void setSteeringWheelAngle(Double steeringWheelAngle) {
        if (steeringWheelAngle != null) {
            parameters.put(KEY_STEERING_WHEEL_ANGLE, steeringWheelAngle);
        } else {
        	parameters.remove(KEY_STEERING_WHEEL_ANGLE);
        }
    }
    public Double getSteeringWheelAngle() {
    	Object object = parameters.get(KEY_STEERING_WHEEL_ANGLE);
    	return SdlDataTypeConverter.objectToDouble(object);
    }

    public void setSteeringWheelAngleArray(List<SteeringWheelAngleType> steeringWheelAngleArray){
        if(steeringWheelAngleArray != null){
            parameters.put(KEY_STEERING_WHEEL_ANGLE_ARRAY, steeringWheelAngleArray);
        }
        else{
            parameters.remove(KEY_STEERING_WHEEL_ANGLE_ARRAY);
        }
    }

    @SuppressWarnings("unchecked")
    public List<SteeringWheelAngleType> getSteeringWheelAngleArray(){
        if(parameters.get(KEY_STEERING_WHEEL_ANGLE_ARRAY) instanceof List<?>){
            List<?> list = (List<?>) parameters.get(KEY_STEERING_WHEEL_ANGLE_ARRAY);
            if(list != null && list.size() > 0){
                Object obj = list.get(0);
                if(obj instanceof SteeringWheelAngleType){
                    return (List<SteeringWheelAngleType>) list;
                }
                else if(obj instanceof Hashtable){
                    List<SteeringWheelAngleType> newList = new ArrayList<SteeringWheelAngleType>();
                    for(Object hashObj : list){
                        newList.add(new SteeringWheelAngleType((Hashtable<String, Object>) hashObj));
                    }
                    return newList;
                }
            }
        }
        return null;
    }

    public void setECallInfo(ECallInfo eCallInfo) {
        if (eCallInfo != null) {
            parameters.put(KEY_E_CALL_INFO, eCallInfo);
        } else {
        	parameters.remove(KEY_E_CALL_INFO);
        }
    }
    @SuppressWarnings("unchecked")
    public ECallInfo getECallInfo() {
    	Object obj = parameters.get(KEY_E_CALL_INFO);
        if (obj instanceof ECallInfo) {
            return (ECallInfo) obj;
        } else if (obj instanceof Hashtable) {
        	try {
        		return new ECallInfo((Hashtable<String, Object>) obj);
            } catch (Exception e) {
            	DebugTool.logError("Failed to parse " + getClass().getSimpleName() + "." + KEY_E_CALL_INFO, e);
            }
        }
        return null;
    }
    public void setAirbagStatus(AirbagStatus airbagStatus) {
        if (airbagStatus != null) {
            parameters.put(KEY_AIRBAG_STATUS, airbagStatus);
        } else {
        	parameters.remove(KEY_AIRBAG_STATUS);
        }
    }
    @SuppressWarnings("unchecked")
    public AirbagStatus getAirbagStatus() {
    	Object obj = parameters.get(KEY_AIRBAG_STATUS);
        if (obj instanceof AirbagStatus) {
            return (AirbagStatus) obj;
        } else if (obj instanceof Hashtable) {
        	try {
        		return new AirbagStatus((Hashtable<String, Object>) obj);
            } catch (Exception e) {
            	DebugTool.logError("Failed to parse " + getClass().getSimpleName() + "." + KEY_AIRBAG_STATUS, e);
            }
        }
        return null;
    }
    public void setEmergencyEvent(EmergencyEvent emergencyEvent) {
        if (emergencyEvent != null) {
            parameters.put(KEY_EMERGENCY_EVENT, emergencyEvent);
        } else {
        	parameters.remove(KEY_EMERGENCY_EVENT);
        }
    }
    @SuppressWarnings("unchecked")
    public EmergencyEvent getEmergencyEvent() {
    	Object obj = parameters.get(KEY_EMERGENCY_EVENT);
        if (obj instanceof EmergencyEvent) {
            return (EmergencyEvent) obj;
        } else if (obj instanceof Hashtable) {
        	try {
        		return new EmergencyEvent((Hashtable<String, Object>) obj);
            } catch (Exception e) {
            	DebugTool.logError("Failed to parse " + getClass().getSimpleName() + "." + KEY_EMERGENCY_EVENT, e);
            }
        }
        return null;
    }
    public void setClusterModeStatus(ClusterModeStatus clusterModeStatus) {
        if (clusterModeStatus != null) {
            parameters.put(KEY_CLUSTER_MODE_STATUS, clusterModeStatus);
        } else {
        	parameters.remove(KEY_CLUSTER_MODE_STATUS);
        }
    }
    @SuppressWarnings("unchecked")
    public ClusterModeStatus getClusterModeStatus() {
    	Object obj = parameters.get(KEY_CLUSTER_MODE_STATUS);
        if (obj instanceof ClusterModeStatus) {
            return (ClusterModeStatus) obj;
        } else if (obj instanceof Hashtable) {
        	try {
        		return new ClusterModeStatus((Hashtable<String, Object>) obj);
            } catch (Exception e) {
            	DebugTool.logError("Failed to parse " + getClass().getSimpleName() + "." + KEY_CLUSTER_MODE_STATUS, e);
            }
        }
        return null;
    }
    public void setMyKey(MyKey myKey) {
        if (myKey != null) {
            parameters.put(KEY_MY_KEY, myKey);
        } else {
        	parameters.remove(KEY_MY_KEY);
        }
    }
    @SuppressWarnings("unchecked")
    public MyKey getMyKey() {
    	Object obj = parameters.get(KEY_MY_KEY);
        if (obj instanceof MyKey) {
            return (MyKey) obj;
        } else if (obj instanceof Hashtable) {
        	try {
        		return new MyKey((Hashtable<String, Object>) obj);
            } catch (Exception e) {
            	DebugTool.logError("Failed to parse " + getClass().getSimpleName() + "." + KEY_MY_KEY, e);
            }
        }
        return null;
    }

    public void setAccelerometerArray(List<AxisSensorDataType> accelerometerArray){
        if(accelerometerArray != null){
            parameters.put(KEY_ACCELEROMETER_ARRAY, accelerometerArray);
        }
        else{
            parameters.remove(KEY_ACCELEROMETER_ARRAY);
        }
    }

    @SuppressWarnings("unchecked")
    public List<AxisSensorDataType> getAccelerometerArray(){
        if(parameters.get(KEY_ACCELEROMETER_ARRAY) instanceof List<?>){
            List<?> list = (List<?>) parameters.get(KEY_ACCELEROMETER_ARRAY);
            if(list != null && list.size() > 0){
                Object obj = list.get(0);
                if(obj instanceof AxisSensorDataType){
                    return (List<AxisSensorDataType>) list;
                }
                else if(obj instanceof Hashtable){
                    List<AxisSensorDataType> newList = new ArrayList<AxisSensorDataType>();
                    for(Object hashObj : list){
                        newList.add(new AxisSensorDataType((Hashtable<String, Object>) hashObj));
                    }
                    return newList;
                }
            }
        }
        return null;
    }

    public void setGyroscopeArray(List<AxisSensorDataType> gyroscopeArray){
        if(gyroscopeArray != null){
            parameters.put(KEY_GYROSCOPE_ARRAY, gyroscopeArray);
        }
        else{
            parameters.remove(KEY_GYROSCOPE_ARRAY);
        }
    }

    @SuppressWarnings("unchecked")
    public List<AxisSensorDataType> getGyroscopeArray(){
        if(parameters.get(KEY_GYROSCOPE_ARRAY) instanceof List<?>){
            List<?> list = (List<?>) parameters.get(KEY_GYROSCOPE_ARRAY);
            if(list != null && list.size() > 0){
                Object obj = list.get(0);
                if(obj instanceof AxisSensorDataType){
                    return (List<AxisSensorDataType>) list;
                }
                else if(obj instanceof Hashtable){
                    List<AxisSensorDataType> newList = new ArrayList<AxisSensorDataType>();
                    for(Object hashObj : list){
                        newList.add(new AxisSensorDataType((Hashtable<String, Object>) hashObj));
                    }
                    return newList;
                }
            }
        }
        return null;
    }

    public void setWheelSpeedsArray(List<WheelSpeedsDataType> wheelSpeedsArray){
        if(wheelSpeedsArray != null){
            parameters.put(KEY_WHEEL_SPEEDS_ARRAY, wheelSpeedsArray);
        }
        else{
            parameters.remove(KEY_WHEEL_SPEEDS_ARRAY);
        }
    }

    @SuppressWarnings("unchecked")
    public List<WheelSpeedsDataType> getWheelSpeedsArray(){
        if(parameters.get(KEY_WHEEL_SPEEDS_ARRAY) instanceof List<?>){
            List<?> list = (List<?>) parameters.get(KEY_WHEEL_SPEEDS_ARRAY);
            if(list != null && list.size() > 0){
                Object obj = list.get(0);
                if(obj instanceof WheelSpeedsDataType){
                    return (List<WheelSpeedsDataType>) list;
                }
                else if(obj instanceof Hashtable){
                    List<WheelSpeedsDataType> newList = new ArrayList<WheelSpeedsDataType>();
                    for(Object hashObj : list){
                        newList.add(new WheelSpeedsDataType((Hashtable<String, Object>) hashObj));
                    }
                    return newList;
                }
            }
        }
        return null;
    }

}
