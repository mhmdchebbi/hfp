package net.heberd.hfp.adapters;

import org.apache.camel.builder.RouteBuilder;

public class EdifactAdapter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("file:input/edi?noop=true&include=.*\\.edi")
				.routeId("edifact-file-to-smooks")
				.log("Picked up EDIFACT file: ${header.CamelFileName}")
				.to("smooks:smooks-edifact-config.xml")
				.log("Parsed EDIFACT as XML: ${body}")
				.to("file:output/processed");
	}

}
