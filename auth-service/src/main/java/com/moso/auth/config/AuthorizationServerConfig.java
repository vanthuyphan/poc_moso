package com.moso.auth.config;

import com.moso.auth.properties.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@RequiredArgsConstructor
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  private static final String DEFAULT_SCOPE = "all";

  private final TokenStore tokenStore;
  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;
  private final ApplicationProperties properties;

  @SuppressWarnings("deprecation")
  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
    oauthServer
        .passwordEncoder(NoOpPasswordEncoder.getInstance())
        .checkTokenAccess("permitAll()")
        .allowFormAuthenticationForClients();
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
        .withClient(properties.getOauth().getClientId())
        .secret(properties.getOauth().getClientSecret())
        .authorizedGrantTypes("password", "refresh_token")
        .scopes(DEFAULT_SCOPE)
        .autoApprove(true);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
        .tokenStore(tokenStore)
        .authenticationManager(authenticationManager)
        .userDetailsService(userDetailsService);
  }
}
