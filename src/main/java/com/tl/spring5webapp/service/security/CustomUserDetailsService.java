package com.tl.spring5webapp.service.security;

import com.tl.spring5webapp.model.UserRole;
import com.tl.spring5webapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  private static List<GrantedAuthority> getAuthorities(Collection<UserRole> roles) {
    return roles.stream()
        .map(UserRole::getName)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toUnmodifiableList());
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("None user have leading username: " + username);
    }

    return new org.springframework.security.core.userdetails.User(
        user.getUsername(),
        user.getPassword().toLowerCase(),
        true,
        true,
        true,
        true,
        getAuthorities(user.getRoles()));
  }
}
