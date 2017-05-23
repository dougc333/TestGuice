package com.palominolabs.benchpress.example.multidb.mongodb;

import com.palominolabs.benchpress.example.multidb.key.KeyGeneratorFactory;
import com.palominolabs.benchpress.example.multidb.task.ComponentFactoryBase;
import com.palominolabs.benchpress.example.multidb.key.KeyGeneratorFactoryFactoryRegistry;
import com.palominolabs.benchpress.example.multidb.value.ValueGeneratorFactory;
import com.palominolabs.benchpress.job.task.ComponentFactory;
import com.palominolabs.benchpress.job.task.TaskFactory;
import com.palominolabs.benchpress.job.task.TaskOutputProcessorFactory;
import com.palominolabs.benchpress.example.multidb.value.ValueGeneratorFactoryFactoryRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

final class MongoDbComponentFactory extends ComponentFactoryBase implements ComponentFactory {

    private final MongoDbConfig config;

    MongoDbComponentFactory(
        KeyGeneratorFactoryFactoryRegistry keyGeneratorFactoryFactoryRegistry,
        ValueGeneratorFactoryFactoryRegistry valueGeneratorFactoryFactoryRegistry, MongoDbConfig config) {
        super(keyGeneratorFactoryFactoryRegistry, valueGeneratorFactoryFactoryRegistry);
        this.config = config;
    }

    @Nonnull
    @Override
    public TaskFactory getTaskFactory() {
        System.out.println("++++++++MongoDbComponentFactory getTaskFactory()");
        System.out.println("++++++++MongodDBComponentFactory config:"+config.toString());
        System.out.println("++++++++MongodDBComponentFactory test config.getKeyGen():"+config.getKeyGen().toString());
        System.out.println("++++++++MongodDBComponentFactory test config.getKeyGen().keyGenType:"+config.getKeyGen().keyGenType);

        System.out.println("++++++++MongodDBComponentFactory test config.getValueGen() Am I valid:"+config.getValueGen().toString());
        System.out.println("++++++++MongodDBComponentFactory test config.getValueGen().getValueGenType() Am I valid:"+config.getValueGen().getValueGenType());
        System.out.println("++++++++MongodDBComponentFactory test config.getValueGen().getConfig() Am I valid:"+config.getValueGen().getConfig());

        //System.out.println("++++++++MongodDBComponentFactory test config.getValueGen().getConfig().getSize:"+config.getValueGen().getConfig().getInt("size"));
        System.out.println("++++++++MondoDBComponentFactor test did you see size above? ");
        //lets test the ctor here.. first
        System.out.println("++++++++MongoDBComonentFactory before mongoDBTaskFactory ctor!!!");
        System.out.println("++++++++MongoDBComponentFactory test ctor params taskOp:"+config.getTaskOperation().toString());
        System.out.println("++++++++MongoDBComponentFactory test ctor params BatchSize:"+config.getBatchSize());
        //keygenfactory is used how? in taskfactory?
        //it just assigns teh KeyGenFactory to the taskFactory to make the ctor. Why is ID invalid? Is a string, ZERO_BYTE_ARRAY
        //how is the jobregistry involved in the KeyGenFactory?, getType, getConfig

        System.out.println("++++++++MongoDBComponentFactory test ctor params numQuata:"+config.getNumQuanta());
        System.out.println("++++++++MongoDBComponentFactory test ctor params numThreads:"+config.getNumThreads());
        System.out.println("++++++++MongoDBComponentFactory test ctor params hostName:"+config.getHostname());
        System.out.println("++++++++MongoDBComponentFactory test ctor params port:"+config.getPort());
        System.out.println("++++++++MongoDBComponentFactory test ctor params dbName:"+config.getDbName());
        System.out.println("++++++++MongoDBComponentFactory test ctor params collectionName:"+config.getCollectionName().toString());

        System.out.println("Test getValueGeneratorFactory");
        //ValueGeneratorFactory valueGeneratorFactory = getValueGeneratorFactory(config);
        //if (valueGeneratorFactory !=null) {
        //    System.out.println("VGF not null");
        //}else{
        //    System.out.println("value gen factory here...");
        //}

        System.out.println("Test getKeyGeneratorFactory");
        KeyGeneratorFactory keyGeneratorFactory = getKeyGeneratorFactory(config);
        if (keyGeneratorFactory !=null) {
            System.out.println("KGF not null");
        }else{
            System.out.println("key gen factory here...");
        }


    MongoDbTaskFactory mongoDbTaskFactory = new MongoDbTaskFactory(config.getTaskOperation(), getValueGeneratorFactory(config),
                config.getBatchSize(), getKeyGeneratorFactory(config), config.getNumQuanta(), config.getNumThreads(),
                config.getHostname(), config.getPort(), config.getDbName(), config.getCollectionName());

        System.out.println("++++++++mongodbTaskFactory ctor:"+mongoDbTaskFactory.toString());
        return mongoDbTaskFactory;
    }

    @Nullable
    @Override
    public TaskOutputProcessorFactory getTaskOutputProcessorFactory() {
        return null;
    }
}
