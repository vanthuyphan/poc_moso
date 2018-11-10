package com.moso.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "moso.auth-service")
public class ApplicationProperties {

  @NestedConfigurationProperty
  private Oauth oauth = new Oauth();

  @NestedConfigurationProperty
  private UsernameAndPassword superuser = new UsernameAndPassword();

  @Data
  public static class Oauth {

    private String clientId;
    private String clientSecret;
  }

  @Data
  public static class UsernameAndPassword {

    private String username;
    private String encodedPassword;
  }
}
