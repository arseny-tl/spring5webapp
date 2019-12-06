package com.tl.spring5webapp.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@Getter
@Setter
public class UserRole extends BaseEntity {
  private String name;

  @ManyToMany
  @JoinTable(
      name = "user_auth",
      joinColumns = @JoinColumn(name = "auth_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private Set<User> users = new HashSet<>();

  public UserRole() {}

  public UserRole(String name) {
    this.name = name;
  }

  public void addUser(User user) {
    if (users == null) {
      users = new HashSet<>();
    }
    users.add(user);
  }

  public void removeUser(User user) {
    if (users == null) {
      users = new HashSet<>();
    }
    users.remove(user);
  }
}
