package ru.tusur.udo.sensors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class SensorRoutes extends RouteBuilder {

	private EmulationProcessor processor; 
	
	public EmulationProcessor getProcessor() {
		return processor;
	}

	public void setProcessor(EmulationProcessor processor) {
		this.processor = processor;
	}

	@Override
	public void configure() throws Exception {
		from("timer://timer?period=1000").process(this.processor);
	}

}
