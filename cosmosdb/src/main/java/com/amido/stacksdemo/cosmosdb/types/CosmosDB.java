package com.amido.stacksdemo.cosmosdb.types;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CosmosDB implements StacksCosmosRepository<String> {

  public void usingCosmosDB() {
    log.debug("PERSIST USING COSMOSDB");
  }

  @PostConstruct
  public void loaded() {

    log.debug("... COSMOSDB Loaded");
  }
}
