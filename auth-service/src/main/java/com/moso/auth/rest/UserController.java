package com.moso.auth.rest;

import com.moso.auth.domain.User;
import com.moso.auth.service.UserService;
import com.moso.auth.service.UserUpdateRequest;
import java.security.Principal;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @GetMapping("/me")
  public Optional<User> getPerson(Principal principal) {
    String email = principal.getName();
    return userService.getUser(email);
  }

  @PutMapping("/")
  public void saveUser(@RequestBody @Valid UserUpdateRequest updateRequest) {
    userService.saveUser(updateRequest);
  }
}
