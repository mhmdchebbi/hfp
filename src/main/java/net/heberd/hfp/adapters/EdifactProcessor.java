package net.heberd.hfp.adapters;

import io.netty.channel.Channel;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.netty.NettyConstants;
import org.apache.commons.lang3.StringUtils;

public class EdifactProcessor implements Processor {
    public String header;

    @Override
    public void process(Exchange exchange) throws Exception {
        Channel channel = exchange.getProperty(NettyConstants.NETTY_CHANNEL, Channel.class);
        String payload = exchange.getMessage().getBody(String.class);
        String edifact = "UNB" + StringUtils.substringAfter(payload, "UNB");
        this.header = StringUtils.substringBefore(payload, "UNB");
        exchange.getMessage().setBody(edifact);
        //exchange.getMessage().setHeader("Ack", StringUtils.substringBefore(payload, "UNB"));

        char nullCharacter=0;

        String headerAck = StringUtils.rightPad("HEADER_COOKIE", 16, nullCharacter)
                + StringUtils.rightPad("1", 16, nullCharacter)
                + StringUtils.rightPad("ACKNOWLEDGMENT", 16, nullCharacter)
                + StringUtils.rightPad("", 16, nullCharacter)
                + "BSCS-VMDBSCS-GMD"
                + StringUtils.rightPad("96", 16, nullCharacter);

        String edifactAck = StringUtils.rightPad("0", 16, nullCharacter)
                + StringUtils.rightPad("", 80, nullCharacter);



        channel.write(headerAck);

        Thread.sleep(100);

        channel.write(edifactAck);
    }
}
