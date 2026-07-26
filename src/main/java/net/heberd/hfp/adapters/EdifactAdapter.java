package net.heberd.hfp.adapters;

import org.apache.camel.builder.RouteBuilder;

public class EdifactAdapter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		//from("file:input/edi?noop=true&include=.*\\.edi")
		from("netty:tcp://localhost:7070?textline=false")
				.log("Received message: ${body}")
				.routeId("edifact-file-to-smooks")
				.to("smooks:smooks-edifact-config.xml")
				.log("Parsed EDIFACT as XML: ${body}")
				.to("file:output/processed");
	}

}
