package com.amido.stacksdemo.aws.types;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AWS {

  public void usingAWS() {
    log.debug("USING AWS");
  }

  @PostConstruct
  public void loaded() {

    log.debug("... AWS Loaded");
  }
}
