package jerseyserver;

import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * A ResourceConfig that already has some baasic config applied.
 */
public class JerseyResourceConfigBase extends ResourceConfig {

    /**
     * @param objectMapperContextResolver The {@link ObjectMapperContextResolver} to use for Jackson/Jersey integration,
     *                                    typically available with an {@link com.palominolabs.benchpress.ipc.Ipc}
     *                                    binding annotation.
     */
    public JerseyResourceConfigBase(ObjectMapperContextResolver objectMapperContextResolver) {
        register(JacksonFeature.class);
        register(objectMapperContextResolver);

        property(CommonProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);
        property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);
        property(CommonProperties.JSON_PROCESSING_FEATURE_DISABLE, true);
        property(CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);
    }
}