package com.amido.stacksdemo.aws.types;

import com.amido.stacksdemo.commons.types.IProvideCloud;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AWS implements IProvideCloud {

  @PostConstruct
  public void loaded() {

    log.debug("... AWS Loaded");
  }
}
