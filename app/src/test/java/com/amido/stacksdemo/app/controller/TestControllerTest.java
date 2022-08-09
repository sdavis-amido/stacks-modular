package com.amido.stacksdemo.app.controller;

import com.amido.stacksdemo.cosmosdb.types.CosmosDB;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@Tag("unit")
@SpringBootTest
public class TestControllerTest {

  #if DYNAMODB
  @Autowired
  private DynamoDB dynamoDB;

  #elif COSMOSDB
  @Autowired
  private CosmosDB cosmosDB;
  #endif

  @Test
  void shouldRunDatabaseTest() {

  #if DYNAMODB
    dynamoDB.usingDynamoDB();

  #elif COSMOSDB
    cosmosDB.usingCosmosDB();
  #endif

  }

}
