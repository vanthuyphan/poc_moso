package com.moso.userprofile.service;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserProfileUpdateRequest {

  @Email
  private String email;

  @NotBlank
  private String firstName;

  private String middleName;

  @NotBlank
  private String lastName;

  private String phoneNumber;
}
