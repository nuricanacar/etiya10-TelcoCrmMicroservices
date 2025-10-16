package com.etiya.customerservice.transport.kafka.producer.contactMedium;

import com.etiya.common.events.address.DeleteAddressEvent;
import com.etiya.common.events.contactMedium.DeleteContactMediumEvent;
import com.etiya.customerservice.transport.kafka.producer.address.DeleteAddressProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class DeleteContactMediumProducer {

    private final StreamBridge streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteContactMediumProducer.class);

    public DeleteContactMediumProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void produceContactMediumDeleted(DeleteContactMediumEvent event){
        streamBridge.send("contactmediumDeleted-out-0", event);
        LOGGER.info(String.format("Contact Medium deleted event => %s",event.id()));
    }
}
