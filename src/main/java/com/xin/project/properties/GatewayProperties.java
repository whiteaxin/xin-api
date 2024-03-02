package com.xin.project.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "xin-api.gateway")
@Data
public class GatewayProperties {

    private String host;

}
