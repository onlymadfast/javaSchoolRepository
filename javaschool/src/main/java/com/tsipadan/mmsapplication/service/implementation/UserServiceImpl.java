package com.tsipadan.mmsapplication.service.implementation;

import com.tsipadan.dto.UserAddressDTO;
import com.tsipadan.dto.UserDTO;
import com.tsipadan.mmsapplication.entity.User;
import com.tsipadan.mmsapplication.entity.UserAddress;
import com.tsipadan.mmsapplication.entity.UserRole;
import com.tsipadan.mmsapplication.exception.ResourceCreationException;
import com.tsipadan.mmsapplication.mapper.UserAddressMapper;
import com.tsipadan.mmsapplication.mapper.UserMapper;
import com.tsipadan.mmsapplication.repository.api.UserAddressRepository;
import com.tsipadan.mmsapplication.repository.api.UserRepository;
import com.tsipadan.mmsapplication.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserAddressRepository addressRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final UserMapper mapper;
  private final UserAddressMapper addressMapper;

  @Override
  public UserDTO findByUserName(String username) {
    final User user = Optional.of(username)
        .map(userRepository::findByUsername)
        .orElseThrow(()-> new ResourceCreationException("exception"));
    return mapper.toDto(user);
  }

  @Override
  public UserAddressDTO findAddressByUsername(String username){
    final UserAddress userAddress = Optional.of(username)
        .map(addressRepository::findUserAddressByUser_Username)
        .orElse(null);
    return addressMapper.toDto(userAddress);
  }

  @Override
  public boolean save(UserDTO userDTO) {
    Optional<User> userDb  = Optional.ofNullable(userRepository.findByUsername(userDTO.getUsername()));
    if (userDb.isPresent()||userDTO.getUsername().equals("admin")){
      return false;
    }
    User user = mapper.toEntity(userDTO);
    user.setUserRoles(Collections.singleton(new UserRole(1L, "ROLE_CUSTOMER")));
    user.setPassword((bCryptPasswordEncoder.encode(userDTO.getPassword())));
    userRepository.save(user);
    Set<GrantedAuthority> authorities = new HashSet<>();
    authorities.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
    Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return true;
  }

  @Override
  public void update(UserDTO userDTO) {
    User user = userRepository.findById(userDTO.getId());
    user.setUserFirstName(userDTO.getUserFirstName());
    user.setUserLastName(userDTO.getUserLastName());
    user.setUserBirthday(userDTO.getUserBirthday());
    user.setUserEmail(userDTO.getUserEmail());
  }

  @Override
  public void updatePassword(String newPassword, String userName){
    User userDb = userRepository.findByUsername(userName);
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = bCryptPasswordEncoder.encode(newPassword);
    userDb.setPassword(encodedPassword);
    userRepository.save(userDb);
  }

  @Override
  public void updateAddress(UserAddressDTO userAddressDTO){
    UserAddress dB = addressRepository.findById(userAddressDTO.getId());
    dB.setUserCountry(userAddressDTO.getUserCountry());
    dB.setUserCity(userAddressDTO.getUserCity());
    dB.setUserZip(userAddressDTO.getUserZip());
    dB.setUserStreet(userAddressDTO.getUserStreet());
    dB.setUserHouse(userAddressDTO.getUserHouse());
    dB.setUserApartment(userAddressDTO.getUserApartment());
  }

  @Override
  public UserDTO findById(long id) {
    return mapper.toDto(findUserById(id));
  }

  private User findUserById(long id){
    return userRepository.findById(id);
  }

}
