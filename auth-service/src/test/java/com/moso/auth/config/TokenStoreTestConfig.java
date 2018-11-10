package com.moso.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

public class TokenStoreTestConfig {

  @Bean
  @Primary
  public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
    return new InMemoryTokenStore();
  }
}
