package com.palominolabs.benchpress.example.multidb.cassandra;

import com.palominolabs.benchpress.example.multidb.task.TaskPartitionerBase;
import com.palominolabs.benchpress.job.task.TaskPartitioner;

import javax.annotation.Nonnull;

final class CassandraPartitioner extends TaskPartitionerBase implements TaskPartitioner {

    private final CassandraConfig cassandraConfig;

    CassandraPartitioner(CassandraConfig cassandraConfig) {
        this.cassandraConfig = cassandraConfig;
    }

    @Nonnull
    @Override
    protected CassandraConfig getConfig() {
        return cassandraConfig;
    }

    @Nonnull
    @Override
    protected String getTaskType() {
        return CassandraTaskPlugin.TASK_TYPE;
    }
}
