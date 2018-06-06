package ru.tusur.udo.sensors;

import java.util.List;
//import java.util.Properties;
import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.camel.component.ActiveMQComponent;
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
	public ConnectionFactory connectionFactory() throws NamingException {
		Properties ENV = new Properties() {
			{
				put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
				put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
			}
		};
		Context c = new InitialContext(ENV);
		return (ConnectionFactory) c.lookup("jms/RemoteConnectionFactory");
	}

	@Bean
	public ActiveMQComponent activeMQComponent() throws NamingException {
		ActiveMQComponent amqc = ActiveMQComponent.activeMQComponent();
		amqc.setConnectionFactory(connectionFactory());
		return amqc;
	}

	@Bean
	public CamelContext camelContext() throws Exception {
		DefaultCamelContext camelCtx = new DefaultCamelContext();
		camelCtx.addRoutes(this.sensorRoutes());
		camelCtx.addComponent("activemq", activeMQComponent());
		return camelCtx;
	}

	@Bean
	public List<Sensor> listSensors() {
		return (List<Sensor>) classPathXmlApplicationContext().getBean("sensors");
	}

	@Bean
	public EmulationProcessor emulationProcessor() {
		EmulationProcessor emulationProcessor = new EmulationProcessor();
		emulationProcessor.setListSensors(listSensors());
		return emulationProcessor;
	}

	@Bean
	public JSONProcessor jsonProcessor() {
		return new JSONProcessor();
	}

	@Bean
	public SensorRoutes sensorRoutes() {
		SensorRoutes sensorRoutes = new SensorRoutes();
		sensorRoutes.setEmulProcessor(emulationProcessor());
		sensorRoutes.setJsonProcessor(jsonProcessor());
		return sensorRoutes;
	}

}
