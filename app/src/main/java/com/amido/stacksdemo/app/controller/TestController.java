package com.amido.stacksdemo.app.controller;

#if CLOUD_TYPE_AZURE

import com.amido.stacksdemo.azure.types.Azure;
#elif CLOUD_TYPE_AWS
import com.amido.stacksdemo.aws.types.AWS;
#endif

#if PERSISTENCE_TYPE_DYNAMODB
import com.amido.stacksdemo.dynamodb.types.DynamoDB;
#elif PERSISTENCE_TYPE_COSMOSDB
import com.amido.stacksdemo.cosmosdb.types.CosmosDB;
#endif

#if QUEUE_TYPE_SQS
import com.amido.stacksdemo.sqs.types.SQS;
#elif QUEUE_TYPE_KAFKA
import com.amido.stacksdemo.kafka.types.Kafka;
#elif QUEUE_TYPE_SERVICEBUS
import com.amido.stacksdemo.servicebus.types.ServiceBus;
#endif

import com.amido.stacksdemo.commons.types.CommonStuff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

  #if CLOUD_TYPE_AZURE
  @Autowired
  private Azure azure;
  #elif CLOUD_TYPE_AWS
  @Autowired
  private AWS aws;
  #endif

  #if PERSISTENCE_TYPE_DYNAMODB
  @Autowired
  private DynamoDB dynamoDB;
  #elif PERSISTENCE_TYPE_COSMOSDB
  @Autowired
  private CosmosDB cosmosDB;
  #endif

  #if QUEUE_TYPE_SQS
  @Autowired
  private SQS sqs;
  #elif QUEUE_TYPE_KAFKA
  @Autowired
  private Kafka kafka;
  #elif QUEUE_TYPE_SERVICEBUS
  @Autowired
  private ServiceBus serviceBus;
  #endif

  @Autowired
  private CommonStuff commonStuff;

  @GetMapping
  public ResponseEntity<String> test() {

    #if CLOUD_TYPE_AWS
    aws.usingAWS();
    #elif CLOUD_TYPE_AZURE
    azure.usingAzure();
    #endif

    #if PERSISTENCE_TYPE_DYNAMODB
    dynamoDB.usingDynamoDB();
    #elif PERSISTENCE_TYPE_COSMOSDB
    cosmosDB.usingCosmosDB();
    #endif

    #if QUEUE_TYPE_SQS
    sqs.sendUsingSqs();
    #elif QUEUE_TYPE_KAFKA
    kafka.sendUsingKafka();
    #elif QUEUE_TYPE_SERVICEBUS
    serviceBus.sendUsingServiceBus();
    #endif

    return ResponseEntity.ok("ACK");
  }
}
