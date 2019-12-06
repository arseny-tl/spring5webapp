package com.tl.spring5webapp.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Data
@Entity
public class UserRole {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  @ManyToMany
  @JoinTable(
      name = "user_auth",
      joinColumns = @JoinColumn(name = "auth_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  private Set<User> users = new HashSet<>();

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

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
