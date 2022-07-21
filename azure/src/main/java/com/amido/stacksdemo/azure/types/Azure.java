package com.amido.stacksdemo.azure.types;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Azure {

  public void usingAzure() {
    log.debug("USING AZURE");
  }

  @PostConstruct
  public void loaded() {

    log.debug("... AZURE Loaded");
  }
}
