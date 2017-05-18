package com.palominolabs.benchpress.example.multidb.mongodb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.palominolabs.benchpress.example.multidb.task.TaskConfigBase;
import com.palominolabs.benchpress.job.json.KeyGen;
import com.palominolabs.benchpress.job.json.ValueGen;
import com.palominolabs.benchpress.job.task.TaskOperation;

import javax.annotation.concurrent.Immutable;

@Immutable
class MongoDbConfig extends TaskConfigBase {
    private final String hostname;
    private final int port;
    private final String dbName;
    private final String collectionName;

    @JsonCreator
    MongoDbConfig(@JsonProperty("op") TaskOperation taskOperation, @JsonProperty("threads") int numThreads,
        @JsonProperty("quanta") int numQuanta, @JsonProperty("batchSize") int batchSize,
        @JsonProperty("keyGen") KeyGen keyGen, @JsonProperty("valueGen") ValueGen valueGen,
        @JsonProperty("hostname") String hostname, @JsonProperty("port") int port,
        @JsonProperty("dbName") String dbName, @JsonProperty("collectionName") String collectionName) {
        super(taskOperation, numThreads, numQuanta, batchSize, keyGen, valueGen);

        this.hostname = hostname;
        this.port = port;
        this.dbName = dbName;
        this.collectionName = collectionName;
        System.out.println("+++++++++MongoDBConfig hostname:" + hostname);
        System.out.println("+++++++++MongoDBConfig port:" + port);
        System.out.println("+++++++++MongoDBConfig dbName:" + dbName);
        System.out.println("+++++++++MongoDBConfig collctionName:" + collectionName);


    }

    @JsonProperty("collectionName")
    public String getCollectionName() {
        return collectionName;
    }

    @JsonProperty("dbName")
    public String getDbName() {
        return dbName;
    }

    @JsonProperty("hostname")
    public String getHostname() {
        return hostname;
    }

    @JsonProperty("port")
    public int getPort() {
        return port;
    }

    @Override
    public MongoDbConfig withNewQuanta(int newQuanta) {
        return new MongoDbConfig(getTaskOperation(), getNumThreads(), newQuanta, getBatchSize(), getKeyGen(),
            getValueGen(), hostname, port, dbName, collectionName);
    }
}
