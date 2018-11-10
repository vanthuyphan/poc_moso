package com.moso.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
public class TokenStoreConfig {

  @Bean
  public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
    RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
    redisTokenStore.setPrefix("user-token:");
    return redisTokenStore;
  }
}
