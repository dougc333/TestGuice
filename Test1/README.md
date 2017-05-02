some simple objects with guice
no new() ctors to create object graph
create everything in the modules then do a new Module() and let Guice create the ojbects using @Inject
annotation on the object contructors. Once you add an @Inject to a ctor the method is not public for users
but for guice use only..


