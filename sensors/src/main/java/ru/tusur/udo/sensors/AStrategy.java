package ru.tusur.udo.sensors;

public class AStrategy implements EmulationStrategy {
	
	private int interval;
	private int direction;
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.value = min;
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	private int ticks;
	private int value;
	private int min;
	private int max;
	
	public AStrategy() {
		this.interval = 0;
		this.ticks = 0;
		this.value = 0;
		this.min = 0;
		this.max = 100;
		this.direction = 1;
	}
	public AStrategy(int interval) {
		this.interval = interval;
		this.ticks = 0;
		this.value = 0;
		this.min = 0;
		this.max = 100;
		this.direction = 1;
	}
	public void doEmulate(SensorEmulator emulator) {
		this.ticks ++;
		if (this.ticks == this.interval) {
			this.ticks = 0;
			this.value += this.calcDirection();
		}
		emulator.setValue(this.value);
	}
	
	private int calcDirection() {	
		if (this.value >= this.max) {
			this.direction = -1;
		}
		if (this.value <= this.min) {
			this.direction = 1;
		}
		return this.direction;
	}		
}
