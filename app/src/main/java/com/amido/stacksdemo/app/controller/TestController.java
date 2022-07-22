package com.amido.stacksdemo.app.controller;

#if CLOUD_TYPE_AZURE

import com.amido.stacksdemo.azure.types.Azure;
#else
import com.amido.stacksdemo.aws.types.AWS;
#endif

#if PERSISTENCE_TYPE_DYNAMODB
import com.amido.stacksdemo.dynamodb.types.DynamoDB;
#else
import com.amido.stacksdemo.cosmosdb.types.CosmosDB;
#endif

#if QUEUE_TYPE_SQS
import com.amido.stacksdemo.sqs.types.SQS;
#elif QUEUE_TYPE_KAFKA
import com.amido.stacksdemo.kafka.types.Kafka;
#else
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
  #else
  @Autowired
  private AWS aws;
  #endif


  #if QUEUE_TYPE_SQS
  @Autowired
  private SQS sqs;
  #elif QUEUE_TYPE_KAFKA
  @Autowired
  private Kafka kafka;
  #else
  @Autowired
  private ServiceBus serviceBus;
  #endif

  #if PERSISTENCE_TYPE_DYNAMODB
  @Autowired
  private DynamoDB dynamoDB;
  #else
  @Autowired
  private CosmosDB cosmosDB;
  #endif

  @Autowired
  private CommonStuff commonStuff;

  @GetMapping
  public ResponseEntity<String> test() {

    #if CLOUD_TYPE_AWS
    aws.usingAWS();
    #else
    azure.usingAzure();
    #endif

    #if PERSISTENCE_TYPE_DYNAMODB
    dynamoDB.usingDynamoDB();
    #else
    cosmosDB.usingCosmosDB();
    #endif

    #if QUEUE_TYPE_SQS
    sqs.sendUsingSqs();
    #elif QUEUE_TYPE_KAFKA
    kafka.sendUsingKafka();
    #else
    serviceBus.sendUsingServiceBus();
    #endif

    return ResponseEntity.ok("ACK");
  }
}
