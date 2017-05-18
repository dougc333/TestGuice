package com.palominolabs.benchpress.example.multidb.task;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import com.palominolabs.benchpress.job.id.Identifiable;
import com.palominolabs.benchpress.job.json.Partition;
import com.palominolabs.benchpress.job.json.Task;
import com.palominolabs.benchpress.job.task.TaskPartitioner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nonnull;

/**
 * Convenience base class for TaskFactoryFactory / TaskPartitioner implementations that use TaskConfigBase subclasses.
 */
public abstract class TaskPartitionerBase implements TaskPartitioner {

    @Nonnull
    @Override
    public List<Partition> partition(UUID jobId, int workers, String progressUrl, String finishedUrl,
            ObjectReader objectReader, JsonNode configNode, ObjectWriter objectWriter) throws IOException {

        TaskConfigBase c = getConfig();

        List<Partition> partitions = new ArrayList<>();

        int quantaPerPartition = (int) Math.ceil(c.getNumQuanta() / workers);
        for (int partitionId = 0; partitionId < workers; partitionId++) {
            int newQuanta;
            // TODO
            if (partitionId == workers) {
                newQuanta = quantaPerPartition;
            } else {
                newQuanta = c.getNumQuanta() - quantaPerPartition * (workers - 1);
            }

            TaskConfigBase newConfig = c.withNewQuanta(newQuanta);

            TokenBuffer tokBuf = new TokenBuffer(objectReader, false);
            objectWriter.writeValue(tokBuf, newConfig);
            JsonParser jp = tokBuf.asParser();
            JsonNode newJsonNode =
                    objectReader.readValue(jp, JsonNode.class);
            jp.close();

            Partition partition =
                    new Partition(jobId, partitionId, new Task(getTaskType(), newJsonNode), progressUrl, finishedUrl);
            partitions.add(partition);
        }

        return partitions;
    }

    /**
     * @return task-impl specific config
     */
    @Nonnull
    protected abstract TaskConfigBase getConfig();

    /**
     * @return the task type string that would be in the plugin's {@link Identifiable} implementation and the "type"
     * field of task json.
     */
    @Nonnull
    protected abstract String getTaskType();
}
