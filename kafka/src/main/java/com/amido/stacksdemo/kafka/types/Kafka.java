package com.amido.stacksdemo.kafka.types;

import com.amido.stacksdemo.commons.types.IHandleMessages;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Kafka implements IHandleMessages {

  @PostConstruct
  public void loaded() {

    log.debug("... KAFKA Loaded");
  }
}
