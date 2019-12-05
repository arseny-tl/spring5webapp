package com.tl.spring5webapp.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Data
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String username;
  private String password;

  @ManyToMany(mappedBy = "users")
  private Set<UserRole> roles = new HashSet<>();

  public void addRole(UserRole role) {
    if (roles == null) {
      roles = new HashSet<>();
    }
    this.roles.add(role);
  }

  public void removeRole(UserRole role) {
    if (roles != null) {
      roles.remove(role);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
