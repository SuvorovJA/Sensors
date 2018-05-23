package ru.tusur.udo.sensors;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

	@Bean
	public CamelContext camelContext(){
		DefaultCamelContext camelCtx = new DefaultCamelContext();
		return camelCtx;
	}
	
}
