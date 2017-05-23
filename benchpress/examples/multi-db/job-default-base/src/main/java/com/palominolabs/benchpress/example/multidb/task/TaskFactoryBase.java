package com.palominolabs.benchpress.example.multidb.task;

import com.palominolabs.benchpress.example.multidb.key.KeyGeneratorFactory;
import com.palominolabs.benchpress.job.task.TaskOperation;
import com.palominolabs.benchpress.example.multidb.value.ValueGeneratorFactory;

/**
 * Convenience base class for the default task factories.
 */
public abstract class TaskFactoryBase {
    protected final ValueGeneratorFactory valueGeneratorFactory;
    protected final KeyGeneratorFactory keyGeneratorFactory;
    protected final TaskOperation taskOperation;
    protected final int numThreads;
    protected final int numQuanta;
    protected final int batchSize;

    protected TaskFactoryBase(TaskOperation taskOperation, ValueGeneratorFactory valueGeneratorFactory, int batchSize,
        KeyGeneratorFactory keyGeneratorFactory, int numQuanta, int numThreads) {
        System.out.println("+++++++TaskFactoryBase before ctor ops");
        System.out.println("+++++++TaskFactoryBase params taskOperation:"+taskOperation.toString());

        this.taskOperation = taskOperation;
        this.valueGeneratorFactory = valueGeneratorFactory;
        this.batchSize = batchSize;
        this.keyGeneratorFactory = keyGeneratorFactory;
        this.numQuanta = numQuanta;
        this.numThreads = numThreads;
        System.out.println("+++++++TaskFactryBase complete");
    }

}
