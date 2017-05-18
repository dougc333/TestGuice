package com.palominolabs.benchpress.example.multidb.mongodb;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.palominolabs.benchpress.job.task.TaskPlugin;

public final class MongoDbModule extends AbstractModule {
    @Override
    protected void configure() {
        System.out.println("MongoDB Module configure()");
        //TaskPlugin
        Multibinder.newSetBinder(binder(), TaskPlugin.class).addBinding().to(MongoDbTaskPlugin.class);
    }
}
