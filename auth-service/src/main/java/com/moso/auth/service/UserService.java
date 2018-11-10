package com.moso.auth.service;

import com.moso.auth.dao.UserRepository;
import com.moso.auth.domain.User;
import com.moso.auth.domain.UserRole;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public Optional<User> getUser(String email) {
    return userRepository.findByEmail(email);
  }

  @PreAuthorize("hasRole('" + UserRole.Role.SUPERUSER + "')")
  public void saveUser(UserUpdateRequest updateRequest) {
    String email = updateRequest.getEmail();
    User user = userRepository.findByEmail(email).orElse(new User(email));
    user.setEncodedPassword(passwordEncoder.encode(updateRequest.getPassword()));
    user.setRoles(updateRequest.getRoles());
    userRepository.save(user);
    log.info("Saved user '{}'", email);
  }
}
