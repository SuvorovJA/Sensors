package ru.tusur.udo.sensors;

import junit.framework.TestCase;

class DumbSensor implements SensorEmulator {
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}

public class DStrategyTest extends TestCase {

	private DumbSensor sensorEmulator;

	protected void setUp() throws Exception {
		this.sensorEmulator = new DumbSensor();
		super.setUp();
	}

	public void testDStrategy() {

		int interval = 11;
		int amount = 1000;
		int preVal = 1;
		
		DStrategy ds = new DStrategy(interval);
		assertNotNull(ds);

//		ds.doEmulate(this.sensorEmulator);
//		assertEquals(0, this.sensorEmulator.getValue());
//		ds.doEmulate(this.sensorEmulator);
//		assertEquals(0, this.sensorEmulator.getValue());
//		ds.doEmulate(this.sensorEmulator);
//		assertEquals(0, this.sensorEmulator.getValue());
//		ds.doEmulate(this.sensorEmulator);
//		assertEquals(1, this.sensorEmulator.getValue());
//		ds.doEmulate(this.sensorEmulator);
//		assertEquals(1, this.sensorEmulator.getValue());
//		ds.doEmulate(this.sensorEmulator);
//		assertEquals(1, this.sensorEmulator.getValue());

		for (int i = 0; i < amount; i++) {
			ds.doEmulate(this.sensorEmulator);			
			if (i % interval == 0) {
				if (preVal == 0) {
					preVal = 1;
				} else {
					preVal = 0;
				}
			}
//			System.out.println(preVal + " " + this.sensorEmulator.getValue());			
			assertEquals(preVal, this.sensorEmulator.getValue());
		}
	}
}
