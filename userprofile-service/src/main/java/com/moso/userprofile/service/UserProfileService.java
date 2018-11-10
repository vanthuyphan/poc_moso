package com.moso.userprofile.service;

import com.moso.userprofile.dao.UserProfileRepository;
import com.moso.userprofile.domain.Role;
import com.moso.userprofile.domain.UserProfile;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserProfileService {

  private final UserProfileRepository userProfileRepository;

  @PreAuthorize("#email == authentication.name || hasRole('" + Role.SUPERUSER + "')")
  public Optional<UserProfile> getUser(String email) {
    return userProfileRepository.findByEmail(email);
  }

  @PreAuthorize("#updateRequest.email == authentication.name || hasRole('" + Role.SUPERUSER + "')")
  public void saveUser(UserProfileUpdateRequest updateRequest) {
    String email = updateRequest.getEmail();
    UserProfile userProfile = userProfileRepository.findByEmail(email)
        .orElse(new UserProfile(email));
    userProfile.setFirstName(updateRequest.getFirstName());
    userProfile.setMiddleName(updateRequest.getMiddleName());
    userProfile.setLastName(updateRequest.getLastName());
    userProfile.setPhoneNumber(updateRequest.getPhoneNumber());
    userProfileRepository.save(userProfile);
    log.info("Saved user profile for user '{}'", email);
  }
}
