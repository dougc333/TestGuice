package jerseyserver;

import com.google.inject.Inject;

public class ControllerJerseyApp extends JerseyResourceConfigBase {

    @Inject
    ControllerJerseyApp(@Ipc ObjectMapperContextResolver objectMapperContextResolver) {
        super(objectMapperContextResolver);
        System.out.println("ControllerJerseyApp Inject ctor");
        //HK2.
        register(ControllerJobResource.class);
    }
}
