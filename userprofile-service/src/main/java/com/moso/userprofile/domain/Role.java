package com.moso.userprofile.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Role {

  public static final String ADMIN = "ADMIN";
  public static final String SUPERUSER = "SUPERUSER";
}
