package ru.tusur.udo.sensors;

import junit.framework.TestCase;

class ADumbEmulator implements SensorEmulator {
	private int value;

	public void setValue(int value) {
		this.value = value;				
	}
	public int getValue() {
		return value;
	}
}

public class AStrategyTest extends TestCase {
	private ADumbEmulator emulator;
	protected void setUp() throws Exception {
		this.emulator = new ADumbEmulator();
		super.setUp();
	}
	public void testAStategy() {
		int min = 37;
		int max = 3777;
		int interval = 16;
		int ticksAmount = 25000;
		int testValue = 0;
		AStrategy astrategy = new AStrategy(interval);
		
		assertNotNull(astrategy);
		astrategy.setMin(min);
		astrategy.setMax(max);
		for (int j = 0; j < 100; j++) {
			for(int i = 0; i < (max - min) * interval; i++) {
				astrategy.doEmulate(this.emulator);
				int t = min + (i + 1) / interval;
				assertEquals(this.emulator.getValue(), t);
			}
			assertEquals(this.emulator.getValue(), max);
			for(int i = 0; i < (max - min) * interval; i++) {
				astrategy.doEmulate(this.emulator);
				int t = max - (i + 1) / interval;
				assertEquals(this.emulator.getValue(), t);
			}
			assertEquals(this.emulator.getValue(), min);
		}
	}
}
