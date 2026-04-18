package net.heberd.hfp.adapters;

import org.apache.camel.builder.RouteBuilder;

public class TCPAdapter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("netty:tcp://localhost:44100")
		.to("file:input_box");
		
	}

}
