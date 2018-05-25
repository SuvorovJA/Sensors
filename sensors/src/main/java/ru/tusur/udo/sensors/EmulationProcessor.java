package ru.tusur.udo.sensors;

import org.apache.camel.model.ProcessorDefinition;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class EmulationProcessor implements Processor {

	List<Sensor> listSensors;
	
	public List<Sensor> getListSensors() {
		return listSensors;
	}

	public void setListSensors(List<Sensor> listSensors) {
		this.listSensors = listSensors;
	}

	public void process(Exchange arg0) throws Exception {
		System.out.println("camel processor timer hello ");
		for (Sensor s : this.listSensors){
			((SensorImpl) s).invalidate();
			System.out.println(s.getSerialNumber() + " invalidated");
		}
	}

}