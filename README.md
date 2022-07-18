# Introduction

The purpose of this project is to demonstrate the use of Maven Build Profiles to support modular 
development and deployment of a Java project such as Stacks. The advantage of using build profiles is 
to enforce clear separation of concerns, code execution and deployment using specific features only.

Additionally, a sample bash script called `choose-scenario.sh` is provided that shows how the project
can be configured with a set of fixed features, that converts the project from a profile-project to 
a fixed-project.

# Executing scenarios

## No profile selected

No profiles are selected, so this will error as no `IProvideCloud` interface implementing bean can 
be found by the Spring context at startup: -

```bash
$ cd app
$ mvn clean spring-boot:run
```

```
***************************
APPLICATION FAILED TO START
***************************

Description:

Field cloudProvider in com.amido.stacksdemo.app.controller.TestController required a bean of type 'com.amido.stacksdemo.commons.types.IProvideCloud' that could not be found.

The injection point has the following annotations:
	- @org.springframework.beans.factory.annotation.Autowired(required=true)
```

## Selecting Valid Options

This will work as all required interfaces are provided by implementing beans: -

```bash
$ cd app
$ mvn clean spring-boot:run -P aws,cosmosdb,kafka
```
```
...
2022-07-18 14:49:40.240  INFO 95409 --- [      main] [,] o.a.c.c.C.[.[.[/]                        : Initializing Spring embedded WebApplicationContext
2022-07-18 14:49:40.270 DEBUG 95409 --- [      main] [,] c.a.s.a.c.DebuggingConfig                : CONFIGURING APP...
2022-07-18 14:49:40.275 DEBUG 95409 --- [      main] [,] c.a.s.a.t.AWS                            : ... AWS Loaded
2022-07-18 14:49:40.276 DEBUG 95409 --- [      main] [,] c.a.s.k.t.Kafka                          : ... KAFKA Loaded
2022-07-18 14:49:40.276 DEBUG 95409 --- [      main] [,] c.a.s.c.t.CosmosDB                       : ... COSMOSDB Loaded
```

## Selecting Multiple Overlapping Options

This will error as all multiple interfaces are provided by implementing beans: -

```bash
$ cd app
$ mvn clean spring-boot:run -P aws,cosmosdb,dynamodb,kafka
```
```
...
***************************
APPLICATION FAILED TO START
***************************

Description:

Field dataPersister in com.amido.stacksdemo.app.controller.TestController required a single bean, but 2 were found:
	- cosmosDB: defined in URL [jar:file:/Users/stevedavis/.m2/repository/com/amido/stacksdemo/cosmosdb/0.0.1-SNAPSHOT/cosmosdb-0.0.1-SNAPSHOT.jar!/com/amido/stacksdemo/cosmosdb/types/CosmosDB.class]
	- dynamoDB: defined in URL [jar:file:/Users/stevedavis/.m2/repository/com/amido/stacksdemo/dynamodb/0.0.1-SNAPSHOT/dynamodb-0.0.1-SNAPSHOT.jar!/com/amido/stacksdemo/dynamodb/types/DynamoDB.class]

```

# Configuring for a Project

This is to cater for scenarios where a project wants to take the current codebase and "hard code" it
so that only the options they want to use are provided.

This effectively removes the Maven Build Profiles and moves any dependencies as explicitly required. In 
addition, it also removes any resource files not needed.

One way to test this is to ensure that there are no changes staged in Git and then execute the supplied
bash shell script. It is then possible to observe the changes that have been made to the `pom.xml` and the
resource property files that have been deleted using (for example) the `git status` command or your 
development IDE.

Please note that the script isn't Production grade and allows multiple options to be selected for each
category (where, as shown above in the 'Executing Scenarios' section this could cause lead to startup issues)

```
$ sh choose-scenario.sh

1. Please select the Cloud required:

   [ ]   azure (Azure Cloud)
   [x]   aws (AWS Cloud)

2. Please select the Persistence required:

   [ ]   cosmosdb (CosmosDB)
   [x]   dynamodb (DynamoDB)

3. Please select the Message Handler required:

   [ ]   servicebus (Azure ServiceBus)
   [ ]   kafka (AWS Kafka)
   [x]   sqs (AWS SQS)

You have selected these options for your project:

   * aws
   * dynamodb
   * sqs

Press ENTER to accept or CTRL-C to quit
```