package com.napoleongames.test.service.core.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigCache;

@Sources({
        "classpath:default.properties",
        "classpath:${env}.properties",
        "system:properties",
        "system:env"
})
public interface CommonProperties extends Config {

    @Key("base.url")
    String baseUrl();

    @Key("base.port")
    @DefaultValue("8080")
    int port();

    static CommonProperties getInstance() {
        return ConfigCache.getOrCreate(CommonProperties.class);
    }
}
