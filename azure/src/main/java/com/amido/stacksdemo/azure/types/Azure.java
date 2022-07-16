package com.amido.stacksdemo.azure.types;

import com.amido.stacksdemo.commons.types.IProvideCloud;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Azure implements IProvideCloud {

  @PostConstruct
  public void loaded() {

    log.debug("... AZURE Loaded");
  }
}
