package com.amido.stacksdemo.cosmos.types;

import com.amido.stacksdemo.commons.types.IPersistData;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CosmosDB implements IPersistData {

  @PostConstruct
  public void loaded() {

    log.debug("... COSMOSDB Loaded");
  }
}
