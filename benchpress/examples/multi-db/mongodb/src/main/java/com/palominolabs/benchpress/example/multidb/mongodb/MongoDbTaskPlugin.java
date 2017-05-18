package com.palominolabs.benchpress.example.multidb.mongodb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;
import com.google.inject.Inject;
import com.palominolabs.benchpress.example.multidb.key.KeyGeneratorFactoryFactoryRegistry;
import com.palominolabs.benchpress.job.task.ComponentFactory;
import com.palominolabs.benchpress.job.task.ControllerComponentFactory;
import com.palominolabs.benchpress.job.task.TaskPartitioner;
import com.palominolabs.benchpress.job.task.TaskPlugin;
import com.palominolabs.benchpress.example.multidb.value.ValueGeneratorFactoryFactoryRegistry;

import javax.annotation.Nonnull;
import java.io.IOException;

final class MongoDbTaskPlugin implements TaskPlugin {
    static final String TASK_TYPE = "MONGODB";

    private final KeyGeneratorFactoryFactoryRegistry keyGeneratorFactoryFactoryRegistry;
    private final ValueGeneratorFactoryFactoryRegistry valueGeneratorFactoryFactoryRegistry;

    @Inject
    MongoDbTaskPlugin(KeyGeneratorFactoryFactoryRegistry keyGeneratorFactoryFactoryRegistry,
        ValueGeneratorFactoryFactoryRegistry valueGeneratorFactoryFactoryRegistry) {
        System.out.println("MONDODBTaskPlugin Inject ctor");
        this.keyGeneratorFactoryFactoryRegistry = keyGeneratorFactoryFactoryRegistry;
        this.valueGeneratorFactoryFactoryRegistry = valueGeneratorFactoryFactoryRegistry;
    }

    @Nonnull
    @Override
    public ComponentFactory getComponentFactory(ObjectReader objectReader, JsonNode configNode) throws IOException {
        System.out.println("+++++++++MongoDBTaskPlugin getComponentFactory");
        System.out.println("+++++++++this should be related to mongo.json");
        return new MongoDbComponentFactory(keyGeneratorFactoryFactoryRegistry, valueGeneratorFactoryFactoryRegistry,
            objectReader.forType(MongoDbConfig.class).readValue(configNode));
    }

    @Nonnull
    @Override
    public ControllerComponentFactory getControllerComponentFactory(ObjectReader objectReader,
        JsonNode configNode) throws IOException {
        final MongoDbConfig config = getConfig(objectReader, configNode);
        System.out.println("+++++++++++MongoDBTaskPlugin getControllerComponentFactory");

        return new ControllerComponentFactory() {
            @Nonnull
            @Override
            public TaskPartitioner getTaskPartitioner() {
                System.out.println("++++++++++++MondDBTaskPlugin getTaskPartitioner()");
                return new MongoDbTaskPartitioner(config);
            }
        };
    }

    @Nonnull
    @Override
    public String getRegistryId() {
        System.out.println("++++++++++++MongoDBTaskPlugin getRegistryID()");
        return TASK_TYPE;
    }

    private MongoDbConfig getConfig(ObjectReader objectReader, JsonNode configNode) throws IOException {
        System.out.println("+++++++++++MongoDBTaskPlugin making MongoDBConfig");
        return objectReader.forType(MongoDbConfig.class).readValue(configNode);
    }
}
