package com.moso.userprofile.rest;

import com.moso.userprofile.domain.UserProfile;
import com.moso.userprofile.service.UserProfileService;
import com.moso.userprofile.service.UserProfileUpdateRequest;
import java.security.Principal;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user-profiles")
public class UserProfileController {

  private final UserProfileService userProfileService;

  @GetMapping("/me")
  public ResponseEntity<UserProfile> getPerson(Principal principal) {
    String email = principal.getName();
    return userProfileService.getUser(email)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/")
  public void saveUser(@RequestBody @Valid UserProfileUpdateRequest updateRequest) {
    userProfileService.saveUser(updateRequest);
  }
}
