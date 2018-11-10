package com.moso.userprofile.config;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Configuration
@EnableDiscoveryClient
public class DiscoveryClientConfig {

  private final RemoteTokenServices remoteTokenServices;

  @Bean
  @LoadBalanced
  public RestTemplate remoteTokenServicesRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
      @Override
      // Ignore 400
      public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getRawStatusCode() != 400) {
          super.handleError(response);
        }
      }
    });
    // See https://github.com/spring-cloud/spring-cloud-security/issues/61
    remoteTokenServices.setRestTemplate(restTemplate);
    return restTemplate;
  }
}
