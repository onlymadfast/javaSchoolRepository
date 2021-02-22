package com.tsipadan.mmsapplication.authentication;

import com.tsipadan.mmsapplication.entity.User;
import com.tsipadan.mmsapplication.repository.api.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    final User user = Optional.of(username)
        .map(userRepository::findByUsername)
        .orElseThrow(() -> new UsernameNotFoundException("no user found"));
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    user.getUserRoles().forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getUserRole())));
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
  }

}
