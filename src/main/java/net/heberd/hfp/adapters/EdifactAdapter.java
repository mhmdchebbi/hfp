package net.heberd.hfp.adapters;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.netty.NettyHelper;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

public class EdifactAdapter extends RouteBuilder {

	EdifactProcessor edifactProcessor = new EdifactProcessor();

	@Override
	public void configure() throws Exception {

		//from("file:input/edi?noop=true&include=.*\\.edi")
		from("netty:tcp://localhost:7070?textline=false&sync=true&reuseChannel=true&keepAlive=true")
				.log("Raw message: ${body}")
				.process(edifactProcessor)
				.log("Received message: ${body}")
				.routeId("edifact-file-to-smooks")
				.to("smooks:smooks-edifact-config.xml")
				.log("Parsed EDIFACT as XML: ${body}")
				.to("file:output/processed?fileName=receipt.txt");
	}

}
