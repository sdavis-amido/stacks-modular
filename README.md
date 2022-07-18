# Introduction

The purpose of this project is to demonstrate the use of Maven Build Profiles to support modular 
development and deployment of a Java project such as Stacks. The advantage of using build profiles is 
to enforce clear separation of concerns, code execution and deployment using specific features only.

Additionally, a sample bash script called `choose-scenario.sh` is provided that shows how the project
can be configured with a set of fixed features, that converts the project from a profile-project to 
a fixed-project.

# Executing scenarios

Prior to running tests it is necessary to build install the library code to your local Maven repository. This
can be achieved by running the supplied bash script: -

```bash
$ sh build-libs.sh
```

A number of sample executions are supplied below to show.

## No Maven Build Profile selected

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

## Selecting Valid Maven Build Profile Options

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

## Selecting Multiple Overlapping Maven Build Profile Options

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

# Configuring (hard-coding) for a Project

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

Given that the `pom.xml` now contains no profiles, it is not necessary to provide the `mvn -P` flag. So
starting the application using the set of commands above that originally failed will now work. The
features selected are now 'locked-in' and cannot be altered through configuration.

```bash
$ cd app
$ mvn clean spring-boot:run
```

```
2022-07-18 15:02:24.273  INFO 99686 --- [      main] [,] c.a.s.a.AppApplication                   : The following 3 profiles are active: "aws", "dynamodb", "sqs"
2022-07-18 15:02:24.882  INFO 99686 --- [      main] [,] o.s.b.w.e.t.TomcatWebServer              : Tomcat initialized with port(s): 8080 (http)
2022-07-18 15:02:24.890  INFO 99686 --- [      main] [,] o.a.c.h.Http11NioProtocol                : Initializing ProtocolHandler ["http-nio-8080"]
2022-07-18 15:02:24.890  INFO 99686 --- [      main] [,] o.a.c.c.StandardService                  : Starting service [Tomcat]
2022-07-18 15:02:24.891  INFO 99686 --- [      main] [,] o.a.c.c.StandardEngine                   : Starting Servlet engine: [Apache Tomcat/9.0.64]
2022-07-18 15:02:24.968  INFO 99686 --- [      main] [,] o.a.c.c.C.[.[.[/]                        : Initializing Spring embedded WebApplicationContext
2022-07-18 15:02:24.996 DEBUG 99686 --- [      main] [,] c.a.s.a.c.DebuggingConfig                : CONFIGURING APP...
2022-07-18 15:02:25.000 DEBUG 99686 --- [      main] [,] c.a.s.a.t.AWS                            : ... AWS Loaded
2022-07-18 15:02:25.002 DEBUG 99686 --- [      main] [,] c.a.s.s.t.SQS                            : ... SQS Loaded
2022-07-18 15:02:25.002 DEBUG 99686 --- [      main] [,] c.a.s.d.t.DynamoDB                       : ... DYNAMODB Loaded
```

Undo (or stash) the changes using your Git client to revert the code back to the original project e.g.

```bash
$ git reset HEAD~1
```