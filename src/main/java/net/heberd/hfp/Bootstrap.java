package net.heberd.hfp;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import net.heberd.hfp.adapters.EdifactAdapter;

public class Bootstrap {

	public static void main(String[] args) throws Exception {
		
		try (CamelContext ctx = new DefaultCamelContext()) {
			ctx.addRoutes(new EdifactAdapter());
			ctx.start();

			Thread.sleep(3000);

			ctx.stop();
		}

	}

}
