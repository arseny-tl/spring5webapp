package com.tl.spring5webapp.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {

  private String username;

  private String password;

  @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
  private Set<UserRole> roles = new HashSet<>();

  public User() {}

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

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
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream()
        .map(UserRole::getName)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toUnmodifiableList());
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
