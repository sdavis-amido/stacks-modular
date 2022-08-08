package com.amido.stacksdemo.dynamodb.types;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DynamoDB implements StacksDynamoDBRepository<String> {

  public void usingDynamoDB() {
    log.debug("PERSIST USING DYNAMODB");
  }

  @PostConstruct
  public void loaded() {

    log.debug("... DYNAMODB Loaded");
  }
}
