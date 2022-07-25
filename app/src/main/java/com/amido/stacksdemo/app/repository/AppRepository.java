package com.amido.stacksdemo.app.repository;


#if DYNAMODB
import com.amido.stacksdemo.cosmosdb.types.StacksCosmosRepository;
#elif COSMOSDB
import com.amido.stacksdemo.cosmosdb.types.StacksCosmosRepository;
#endif

import com.amido.stacksdemo.dynamodb.types.StacksDynamoDBRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository
    #if DYNAMODB
    extends StacksDynamoDBRepository<String>
    #elif COSMOSDB
    extends StacksCosmosRepository<String>
    #endif
{}
