package com.moso.auth.security;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Primary
@Component
public class CompositeUserDetailsService implements UserDetailsService {

  private final List<UserDetailsService> userDetailsServiceList;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    for (UserDetailsService userDetailsService : userDetailsServiceList) {
      try {
        return userDetailsService.loadUserByUsername(username);
      } catch (UsernameNotFoundException e) {
        log.debug("{}: {}", userDetailsService.getClass(), e.getMessage());
      }
    }
    throw new UsernameNotFoundException("No user found with username: " + username);
  }
}
