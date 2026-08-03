package net.heberd.hfp.adapters;

import org.apache.camel.builder.RouteBuilder;


public class EdifactAdapter extends RouteBuilder {

	EdifactProcessor edifactProcessor = new EdifactProcessor();

	@Override
	public void configure() throws Exception {

		//from("file:input/edi?noop=true&include=.*\\.edi")
		from("netty:tcp://localhost:7070?textline=false&sync=true&reuseChannel=true")
				.log("Raw message: ${body}")
				.process(edifactProcessor)
				.log("Received message: ${body}")
				.routeId("edifact-file-to-smooks")
				.to("smooks:smooks-edifact-config.xml")
				.log("Parsed EDIFACT as XML: ${body}")
				.to("activemq6:queue:BSCS_RECEIPT_QUEUE");

				from("activemq6:queue:BSCS_RECEIPT_QUEUE")
				.log("Message from ActiveMQ: ${body}")
				.to("file:output/processed?fileName=receipt.txt");
	}

}
