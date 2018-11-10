package com.moso.auth.service;

import com.moso.auth.domain.UserRole;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserUpdateRequest {

  @Email
  private String email;

  @NotBlank
  private String password;

  @NotEmpty
  private Set<UserRole> roles = new HashSet<>();
}
