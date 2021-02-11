package com.tsipadan.mmsapplication.service;

import com.tsipadan.mmsapplication.dto.UserDTO;
import com.tsipadan.mmsapplication.entity.User;
import com.tsipadan.mmsapplication.entity.UserRole;
import com.tsipadan.mmsapplication.exception.ResourceCreationException;
import com.tsipadan.mmsapplication.repository.api.UserRepository;
import com.tsipadan.mmsapplication.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final ModelMapper modelMapper;

  @Override
  public UserDTO findByUserName(String username) {
    final User user = Optional.of(username)
        .map(userRepository::findByUserName)
        .orElseThrow(()-> new ResourceCreationException("exception"));
    return modelMapper.map(user, UserDTO.class);
  }

  @Override
  public UserDTO findById(long id) {
    return modelMapper.map(findUserById(id), UserDTO.class);
  }

  @Override
  public boolean save(UserDTO userDTO) {
    Optional<User> userDb  = Optional.ofNullable(userRepository.findByUserName(userDTO.getUserName()));
    if (userDb.isPresent()||userDTO.getUserName().equals("admin")){
      return false;
    }
    User user = modelMapper.map(userDTO, User.class);
    user.setUserRoles(Collections.singleton(new UserRole(1L, "ROLE_CUSTOMER")));
    user.setUserPassword((bCryptPasswordEncoder.encode(userDTO.getUserPassword())));
    userRepository.save(user);
    Set<GrantedAuthority> authorities = new HashSet<>();
    authorities.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
    Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return true;
  }

  @Override
  public void update(UserDTO userDTO) {
    User user = findUserById(userDTO.getId());
    user.setUserFirstName(userDTO.getUserFirstName());
    user.setUserLastName(userDTO.getUserLastName());
    user.setUserBirthday(userDTO.getUserBirthday());
    user.setUserEmail(userDTO.getUserEmail());
  }

  private User findUserById(final long id){
    return userRepository.findById(id);
  }

  @Transactional
  @Override
  public void updatePassword(String newPassword, String userName){
    User userDb = userRepository.findByUserName(userName);
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = bCryptPasswordEncoder.encode(newPassword);
    userDb.setUserPassword(encodedPassword);
    userRepository.save(userDb);
  }

}
