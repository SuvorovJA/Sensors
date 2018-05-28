package ru.tusur.udo.sensors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class SensorRoutes extends RouteBuilder {

	private EmulationProcessor emulProcessor;
	private JSONProcessor jsonProcessor;

	public JSONProcessor getJsonProcessor() {
		return jsonProcessor;
	}

	public void setJsonProcessor(JSONProcessor jsonProcessor) {
		this.jsonProcessor = jsonProcessor;
	}

	public EmulationProcessor getEmulProcessor() {
		return emulProcessor;
	}

	public void setEmulProcessor(EmulationProcessor processor) {
		this.emulProcessor = processor;
	}

	@Override
	public void configure() throws Exception {

		from("timer://timer?period=1000")
		.process(this.emulProcessor)
		.to("direct:toJSON");

		from("direct:toJSON")
		.process(this.jsonProcessor)
		.to("direct:exit");

		from("direct:exit")
		.process(new Processor() {
			@Override
			public void process(Exchange arg0) throws Exception {
				System.out.println(arg0.getIn().getBody());
			}
		});
	}

}
