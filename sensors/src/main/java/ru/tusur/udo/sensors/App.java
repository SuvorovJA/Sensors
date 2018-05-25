package ru.tusur.udo.sensors;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
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
