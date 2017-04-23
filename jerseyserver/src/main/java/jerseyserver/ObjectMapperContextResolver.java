package jerseyserver;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;

/**
 * Expose a Jackson ObjectMapper to Jersey for JSON mapping.
 */
public final class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {
    private final ObjectMapper objectMapper;

    ObjectMapperContextResolver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return objectMapper;
    }
}
