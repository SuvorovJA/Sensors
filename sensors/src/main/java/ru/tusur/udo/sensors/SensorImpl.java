package ru.tusur.udo.sensors;

public class SensorImpl implements Sensor, SensorEmulator {
	
	private int type;
	private int value;
//	private int status;
	private String serialNumber;
	private EmulationStrategy emulationStrategy;

	public EmulationStrategy getEmulationStrategy() {
		return emulationStrategy;
	}

	public void setEmulationStrategy(EmulationStrategy emulationStrategy) {
		this.emulationStrategy = emulationStrategy;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getType() {
		return this.type;
	}

	public int getValue() {
		return this.value;
	}

//	public int getStatus() {
//		return this.status;
//	}

	public String getSerialNumber() {
		return this.serialNumber;
	}
	
	public void invalidate(){
		this.emulationStrategy.doEmulate(this);
	}

	public void setValue(int value) {
		this.value = value;
		
	}

}
