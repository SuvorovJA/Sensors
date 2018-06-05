package ru.tusur.udo.sensors;

import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		
		Logger logger = LoggerFactory.getLogger(App.class);
		logger.info("Sensors: main");
		
		ApplicationContext ctx2 = new AnnotationConfigApplicationContext(AppContext.class);		
		DefaultCamelContext camelCtx = (DefaultCamelContext) ctx2.getBean("camelContext");
		
		try {
			camelCtx.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Sensors Start.");
		while(true){}
	}
}
