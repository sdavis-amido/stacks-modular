package com.amido.stacksdemo.dynamodb.types;

import com.amido.stacksdemo.commons.types.StacksPersistence;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public interface StacksDynamoDBRepository<T> extends StacksPersistence<T> {

  default void useDynamoDB(Logger log) {
    log.debug("  -> Repo using DynamoDB");
  }
}
