package jerseyserver;

import org.skife.config.Config;
import org.skife.config.Default;

/**
 * Created by dc on 4/21/17.
 * Look at ConfigModuleBuilder.java
 * these 2 lines configure the object for path
 * install(new ConfigModuleBuilder().addConfiguration(new SystemConfiguration()).build());
 * ConfigModule.bindConfigBean(binder(), ControllerConfig.class);
 *
 *
 */
public interface JerseyServerConfig {

    @Config("benchpress.controller.http-server.ip")
    @Default("0.0.0.0")
    String getHttpServerIp();

    @Config("benchpress.controller.http-server.port")
    @Default("7000")
    int getHttpServerPort();


}
