package com.amido.stacksdemo.servicebus.types;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServiceBus {

  public void sendUsingServiceBus() {
    log.debug("SEND USING SERVICEBUS");
  }

  @PostConstruct
  public void loaded() {

    log.debug("... SERVICEBUS Loaded");
  }
}
