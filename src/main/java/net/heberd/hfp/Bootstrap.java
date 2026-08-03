package net.heberd.hfp;

import org.apache.camel.component.activemq6.ActiveMQComponent;
import org.apache.camel.main.Main;
import net.heberd.hfp.adapters.EdifactAdapter;

public class Bootstrap {

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        // 2. Instantiate and configure the ActiveMQ 6 component
        ActiveMQComponent activemq = new ActiveMQComponent();
        activemq.setBrokerURL("tcp://localhost:61617"); // Point to your ActiveMQ 6 broker
        
        // Optional performance configurations
        //activemq.setConcurrentConsumers(5); 

        // 3. Register the component to the context with a schema name
        main.bind("activemq6", activemq);

        // Add routes directly
        main.configure().addRoutesBuilder(new EdifactAdapter());

        // Run the Camel application (blocks until shutdown)
        main.run(args);
    }

}
