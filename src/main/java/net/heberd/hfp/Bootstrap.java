package net.heberd.hfp;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import net.heberd.hfp.adapters.TCPAdapter;

public class Bootstrap {

	public static void main(String[] args) throws Exception {
		
		try (CamelContext ctx = new DefaultCamelContext()) {
			ctx.addRoutes(new TCPAdapter());
		}

	}

}
