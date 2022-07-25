package com.amido.stacksdemo.cosmosdb.types;

import com.amido.stacksdemo.commons.types.StacksPersistence;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public interface StacksCosmosRepository<T> extends StacksPersistence<T> {

  default void useCosmos(Logger log) {
    log.debug("  -> Repo using Cosmos");
  }
}
