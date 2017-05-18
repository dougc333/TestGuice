package com.palominolabs.benchpress.task.simplehttp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import com.palominolabs.benchpress.job.task.ComponentFactory;
import com.palominolabs.benchpress.job.task.ControllerComponentFactory;
import com.palominolabs.benchpress.job.task.TaskPartitioner;
import com.palominolabs.benchpress.job.task.TaskPlugin;
import java.io.IOException;
import javax.annotation.Nonnull;

public final class SimpleHttpTaskPlugin implements TaskPlugin {

    public static final String TASK_TYPE = "simple-http";

    @Inject
    SimpleHttpTaskPlugin() {
    }

    @Nonnull
    @Override
    public ComponentFactory getComponentFactory(ObjectReader objectReader, JsonNode configNode) throws IOException {
        ObjectNode obj = objectReader.forType(ObjectNode.class).readValue(configNode);
        String url = obj.get("url").textValue();

        return new SimpleHttpComponentFactory(url);
    }

    @Nonnull
    @Override
    public ControllerComponentFactory getControllerComponentFactory(ObjectReader objectReader,
            JsonNode configNode) throws IOException {
        return new ControllerComponentFactory() {
            @Nonnull
            @Override
            public TaskPartitioner getTaskPartitioner() {
                return new SimpleHttpTaskPartitioner();
            }
        };
    }

    @Nonnull
    @Override
    public String getRegistryId() {
        return TASK_TYPE;
    }
}
