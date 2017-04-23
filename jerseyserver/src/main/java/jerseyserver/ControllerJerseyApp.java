package jerseyserver;

import com.google.inject.Inject;

public class ControllerJerseyApp extends JerseyResourceConfigBase {

    @Inject
    ControllerJerseyApp(@Ipc ObjectMapperContextResolver objectMapperContextResolver) {
        super(objectMapperContextResolver);

        register(ControllerJobResource.class);
    }
}
