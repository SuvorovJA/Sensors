package ru.tusur.udo.sensors;

import java.util.Date;
import java.util.List;

public interface SensorJsonScheme {
	default String getNode() {
		return null;
	}
	default long getTimeStamp() {
		return new Date().getTime();
	}
	// TODO timezone ?
	default List<Sensor> getSensors(){
		return null;
	}
	
}
