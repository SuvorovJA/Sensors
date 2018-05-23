package ru.tusur.udo.sensors;

import junit.framework.TestCase;

public class SensorImplTest extends TestCase {

	public void testSensorImpl() {
		SensorImpl s = new SensorImpl();
		assertNotNull(s);
		
		s.setSerialNumber("123");
		s.setType(123);
		s.setValue(220);
		
		
		assertEquals("123", s.getSerialNumber());
		assertEquals(123, s.getType());
		assertEquals(220, s.getValue());
		// assertTrue(false);
	}
}
