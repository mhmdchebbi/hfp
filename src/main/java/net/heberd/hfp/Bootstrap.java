package net.heberd.hfp;

import org.apache.camel.main.Main;
import net.heberd.hfp.adapters.EdifactAdapter;

public class Bootstrap {

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        // Add routes directly
        main.configure().addRoutesBuilder(new EdifactAdapter());

        // Run the Camel application (blocks until shutdown)
        main.run(args);
    }

}
