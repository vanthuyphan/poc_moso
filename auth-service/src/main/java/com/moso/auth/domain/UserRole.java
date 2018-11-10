package com.moso.auth.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserRole {

  ADMIN(Role.ADMIN), SUPER_USER(Role.SUPERUSER);

  private final String role;

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public final class Role {

    public static final String ADMIN = "ADMIN";
    public static final String SUPERUSER = "SUPERUSER";
  }
}
