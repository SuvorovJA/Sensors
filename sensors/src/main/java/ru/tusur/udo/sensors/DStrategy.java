package ru.tusur.udo.sensors;

public class DStrategy implements EmulationStrategy {

	private int preVal;
	private int interval;
	private int ticks;

	public DStrategy() {
		this.interval = 0;
		this.ticks = 0;
		this.preVal = 0;
	}

	public DStrategy(int interval) {
		this.interval = interval;
		this.ticks = 0;
		this.preVal = 0;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public void doEmulate(SensorEmulator sensorEmulator) {

		if (this.interval == this.ticks) {
			if (this.preVal == 0) {
				this.preVal = 1;
			} else {
				this.preVal = 0;
			}
			sensorEmulator.setValue(this.preVal);
			ticks = 0;
		}
		this.ticks++;
		
	}

}
