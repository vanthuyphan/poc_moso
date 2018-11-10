package com.moso.auth.security;

import com.moso.auth.dao.UserRepository;
import com.moso.auth.domain.User;
import com.moso.auth.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Order(1)
public class DaoUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username)
        .orElseThrow(
            () -> new UsernameNotFoundException("No user found with username: " + username));
    return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
        .password(user.getEncodedPassword())
        .roles(user.getRoles().stream().map(UserRole::getRole).toArray(String[]::new))
        .build();
  }
}
