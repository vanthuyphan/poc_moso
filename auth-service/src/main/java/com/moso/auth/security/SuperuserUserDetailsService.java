package com.moso.auth.security;

import com.moso.auth.properties.ApplicationProperties;
import com.moso.auth.properties.ApplicationProperties.UsernameAndPassword;
import com.moso.auth.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Order(2)
public class SuperuserUserDetailsService implements UserDetailsService {

  private final ApplicationProperties properties;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UsernameAndPassword superuser = properties.getSuperuser();
    if (!superuser.getUsername().equals(username)) {
      throw new UsernameNotFoundException("No user found with username: " + username);
    }
    return org.springframework.security.core.userdetails.User
        .withUsername(superuser.getUsername())
        .password(superuser.getEncodedPassword())
        .roles(UserRole.Role.ADMIN, UserRole.Role.SUPERUSER)
        .build();
  }
}
