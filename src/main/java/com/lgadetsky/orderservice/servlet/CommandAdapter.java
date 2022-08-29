package com.lgadetsky.orderservice.servlet;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CommandAdapter extends XmlAdapter<String, Command>{

	@Override
	public Command unmarshal(String v) throws Exception {
		if(v.equalsIgnoreCase("create"))
			return Command.CREATE;
		else if (v.equalsIgnoreCase("update"))
			return Command.UPDATE;
		else 
			return Command.DELETE;
	}

	@Override
	public String marshal(Command v) throws Exception {
	
		return v.toString().toLowerCase();
	}

}
