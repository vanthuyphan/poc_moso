package com.moso.auth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "moso_user")
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Long id;

  @Column(unique = true, nullable = false)
  private String email;

  @JsonIgnore
  @Column(nullable = false)
  private String encodedPassword;

  @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  @CollectionTable(name = "moso_user_role",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
  @Column(name = "role_name")
  private Set<UserRole> roles = new HashSet<>();

  public User(String email) {
    this.email = email;
  }
}
