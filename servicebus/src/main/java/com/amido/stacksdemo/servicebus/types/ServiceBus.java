package com.amido.stacksdemo.servicebus.types;

import com.amido.stacksdemo.commons.types.IHandleMessages;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServiceBus implements IHandleMessages {

  @PostConstruct
  public void loaded() {

    log.debug("... SERVICEBUS Loaded");
  }
}
