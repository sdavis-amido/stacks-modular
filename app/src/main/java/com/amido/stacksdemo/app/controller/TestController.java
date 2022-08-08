package com.amido.stacksdemo.app.controller;


#if AZURE

import com.amido.stacksdemo.azure.types.Azure;
#elif AWS

import com.amido.stacksdemo.aws.types.AWS;
#endif

#if DYNAMODB
import com.amido.stacksdemo.dynamodb.types.DynamoDB;
import com.amido.stacksdemo.dynamodb.types.StacksDynamoDBRepository;
#elif COSMOSDB
import com.amido.stacksdemo.cosmosdb.types.CosmosDB;
import com.amido.stacksdemo.cosmosdb.types.StacksCosmosRepository;
#endif

#if SQS
import com.amido.stacksdemo.sqs.types.SQS;
#elif KAFKA
import com.amido.stacksdemo.kafka.types.Kafka;
#elif SERVICEBUS
import com.amido.stacksdemo.servicebus.types.ServiceBus;
#endif

import com.amido.stacksdemo.commons.types.CommonStuff;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
@Slf4j
public class TestController {

  #if AZURE
  @Autowired
  private Azure azure;
  #elif AWS
  @Autowired
  private AWS aws;
  #endif

  #if DYNAMODB
  @Autowired
  private DynamoDB dynamoDB;

  #elif COSMOSDB
  @Autowired
  private CosmosDB cosmosDB;
  #endif

  #if SQS
  @Autowired
  private SQS sqs;
  #elif KAFKA
  @Autowired
  private Kafka kafka;
  #elif SERVICEBUS
  @Autowired
  private ServiceBus serviceBus;
  #endif

  @Autowired
  private CommonStuff commonStuff;

  #if DYNAMODB
  @Autowired
  private StacksDynamoDBRepository<String> dynamoDBrepository;

  #elif COSMOSDB
  @Autowired
  private StacksCosmosRepository<String> cosmosDBrepository;
  #endif

  @GetMapping
  public ResponseEntity<String> test() {
    execute();

    return ResponseEntity.ok("ACK");
  }

  @PostConstruct
  public void execute() {

    #if AWS
    aws.usingAWS();
    #elif AZURE
    azure.usingAzure();
    #endif

    #if DYNAMODB
    dynamoDB.usingDynamoDB();
    dynamoDBrepository.useDynamoDB(log);
    #elif COSMOSDB
    cosmosDB.usingCosmosDB();
    cosmosDBrepository.useCosmos(log);
    #endif

    #if SQS
    sqs.sendUsingSqs();
    #elif KAFKA
    kafka.sendUsingKafka();
    #elif SERVICEBUS
    serviceBus.sendUsingServiceBus();
    #endif

  }
}
