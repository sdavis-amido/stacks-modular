package com.amido.stacksdemo.sqs.types;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SQS {

  public void sendUsingSqs() {
    log.debug("SEND USING SQS");
  }

  @PostConstruct
  public void loaded() {

    log.debug("... SQS Loaded");
  }
}
