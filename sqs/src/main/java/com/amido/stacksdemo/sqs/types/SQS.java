package com.amido.stacksdemo.sqs.types;

import com.amido.stacksdemo.commons.types.IHandleMessages;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SQS implements IHandleMessages {

  @PostConstruct
  public void loaded() {

    log.debug("... SQS Loaded");
  }
}
