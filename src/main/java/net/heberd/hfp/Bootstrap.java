package net.heberd.hfp;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class Bootstrap {

	public static void main(String[] args) throws Exception {
		
		CamelContext ctx = new DefaultCamelContext();
		
		ctx.addRoutes(null);

	}

}
