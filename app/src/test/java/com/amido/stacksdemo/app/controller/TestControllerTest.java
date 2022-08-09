package com.amido.stacksdemo.app.controller;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

#if DYNAMODB
import com.amido.stacksdemo.dynamodb.types.DynamoDB;
import com.amido.stacksdemo.dynamodb.types.StacksDynamoDBRepository;
#elif COSMOSDB
import com.amido.stacksdemo.cosmosdb.types.CosmosDB;
import com.amido.stacksdemo.cosmosdb.types.StacksCosmosRepository;
#endif

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
