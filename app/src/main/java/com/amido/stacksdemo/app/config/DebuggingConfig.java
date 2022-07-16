package com.amido.stacksdemo.app.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DebuggingConfig {

  public DebuggingConfig() {

    log.debug("CONFIGURING APP...");
  }
}
