package ru.tusur.udo.sensors;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
public class AppContext {

	@Bean
	public ClassPathXmlApplicationContext classPathXmlApplicationContext() {
		return new ClassPathXmlApplicationContext("applicationConfig.xml");		
	}
	
	@Bean
	public List<Sensor> listSensors() {
		return (List<Sensor>) classPathXmlApplicationContext().getBean("sensors");
	}
	
	@Bean
	public CamelContext camelContext() throws Exception{
		DefaultCamelContext camelCtx = new DefaultCamelContext();
		camelCtx.addRoutes(this.sensorRoutes());
		return camelCtx;
	}

	@Bean
	public SensorRoutes sensorRoutes(){
		SensorRoutes sensorRoutes =  new SensorRoutes();
		sensorRoutes.setProcessor(emulationProcessor());
		return sensorRoutes;
	}
	
	@Bean
	public EmulationProcessor emulationProcessor() {
		EmulationProcessor emulationProcessor = new EmulationProcessor();
		emulationProcessor.setListSensors(listSensors());
		return emulationProcessor;
	}

}
