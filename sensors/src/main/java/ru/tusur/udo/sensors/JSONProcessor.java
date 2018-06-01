package ru.tusur.udo.sensors;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONProcessor implements Processor {

	@Override
	public void process(Exchange arg0) throws Exception {	
		
//		System.out.println("jsonProcessor");
		
		ObjectMapper mapper = new ObjectMapper();
		List<Sensor> sensors = (List<Sensor>) arg0.getIn().getBody();
		arg0.getIn().setBody(mapper.writeValueAsString(new SensorJsonScheme() {
			public String getNode() { return "TOMSK"; }
			public List<Sensor> getSensors() { return sensors; }
		}));
	}
}
