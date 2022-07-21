package com.amido.stacksdemo.commons.types;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommonStuff {

  @PostConstruct
  public void loaded() {

    log.debug("... COMMON Loaded");
  }
}
