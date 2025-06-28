package dev.abhi.userservice.userservice.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.auth")
@Setter
@Getter
public class AuthProperties {
    private String defaultRole;
}
