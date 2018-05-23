package ru.tusur.udo.sensors;

import junit.framework.TestCase;

class DumbASensor implements SensorEmulator {
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}

class LinearIntegerEquationSaw {

	/**
	 * parameters min, max, period.
	 * 
	 * getY: input x>0. output y.
	 */

	private int mDirect;
	private int mReverse;
	private int poluPeriod;
	private int period;
	private int a1;
	private int a2;
	private int b1;
	private int b2;

	public LinearIntegerEquationSaw(int min, int max, int period) {
		super();

		if (min > max)
			throw new IllegalArgumentException("min>max");

		this.poluPeriod = period / 2;
		this.period = period;

		// generate point x1:y1, point x2:y2, point x3:y3
		int x1 = 0;
		int x2 = this.poluPeriod;
		int x3 = period;
		int y1 = min;
		int y2 = max;
		int y3 = min;

		// a*x + b*y + c = 0 // direct equation coefficients
		a1 = y1 - y2;
		b1 = x2 - x1; // must be != 0
		if (b1 == 0)
			throw new IllegalArgumentException("(x2-x1)=0!");
		this.mDirect = -(((x1 * y2) - (x2 * y1)) / b1);

		// a*x + b*y + c = 0 // reverse equation coefficients
		a2 = y2 - y3;
		b2 = x3 - x2; // must be != 0
		if (b2 == 0)
			throw new IllegalArgumentException("(x3-x2)=0!");
		this.mReverse = -(((x2 * y3) - (x3 * y2)) / b2);
	}

	public int getY(int x) {
		int result;
		if (x < 0)
			throw new IllegalArgumentException("x<0!");
		// reduce x to first period only
		x -= this.period * (x / this.period);
		// y = k*x + m where k=-(a/b) m=-(c/b), if using k indirect then 0*x problem
		// occur for int type
		if (x <= this.poluPeriod) {
			result = -(this.a1 * x) / this.b1 + this.mDirect;
		} else {
			result = -(this.a2 * x) / this.b2 + this.mReverse;
		}
		return result;
	}

}

public class AStrategyTest extends TestCase {

	private DumbASensor sensorEmulator;

	protected void setUp() throws Exception {
		this.sensorEmulator = new DumbASensor();
		super.setUp();
	}

	public void testAStrategy() {

		int maxval = 90;
		int minval = -50;
		int periodInTicks = 20;
		int ticksForTest = 111111111;
		//
		LinearIntegerEquationSaw lies = new LinearIntegerEquationSaw(minval, maxval, periodInTicks); // humor ?

		AStrategy as = new AStrategy();
		assertNotNull(as);
		as.setMaxVal(maxval);
		as.setMinVal(minval);
		as.setInterval(periodInTicks);
		for (int i = 0; i < ticksForTest; i++) {
//			System.out.println(i + " -> " + lies.getY(i));
			as.doEmulate(sensorEmulator);
			assertEquals(lies.getY(i), sensorEmulator.getValue());
		}
		
		AStrategy as2 = new AStrategy(minval,maxval,periodInTicks);
		assertNotNull(as2);
		for (int i = 0; i < ticksForTest; i++) {
//			System.out.println(i + " -> " + lies.getY(i));
			as2.doEmulate(sensorEmulator);
			assertEquals(lies.getY(i), sensorEmulator.getValue());
		}
	}
}
