package com.tsipadan.service.implementation;

import com.tsipadan.dto.UserAddressDTO;
import com.tsipadan.dto.UserDTO;
import com.tsipadan.entity.User;
import com.tsipadan.entity.UserAddress;
import com.tsipadan.entity.UserRole;
import com.tsipadan.exception.EntityNotFoundException;
import com.tsipadan.exception.ResourceCreationException;
import com.tsipadan.mapper.UserAddressMapper;
import com.tsipadan.mapper.UserMapper;
import com.tsipadan.repository.UserAddressRepository;
import com.tsipadan.repository.UserRepository;
import com.tsipadan.service.api.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserAddressRepository addressRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final UserMapper mapper;
  private final UserAddressMapper addressMapper;

  /**
   * Find user
   *
   * @param username - specific username
   * @return userDTO
   */
  @Override
  @Transactional(readOnly = true)
  public UserDTO findByUserName(String username) {
    final User user = Optional.of(username)
        .map(userRepository::findByUsername)
        .orElseThrow(()-> new ResourceCreationException("exception"));
    return mapper.toDto(user);
  }

  /**
   * Find user address
   *
   * @param username - specific username
   * @return userAddressDTO
   */
  @Override
  @Transactional(readOnly = true)
  public UserAddressDTO findAddressByUsername(String username){
    final UserAddress userAddress = Optional.of(username)
        .map(addressRepository::findUserAddressByUser_Username)
        .orElse(null);
    return addressMapper.toDto(userAddress);
  }

  /**
   * Save user in Db
   *
   * @param userDTO - user
   * @return true or false
   */
  @Override
  @Transactional
  public boolean save(final @NonNull UserDTO userDTO) {
    Optional<User> userDb  = Optional.ofNullable(userRepository.findUserByUsername(userDTO.getUsername()));
    if (userDb.isPresent()||userDTO.getUsername().equals("admin")){
      return false;
    }
    final User user = mapper.toEntity(userDTO);
    user.setUserRoles(Collections.singleton(new UserRole(1L, "ROLE_CUSTOMER")));
    user.setPassword((bCryptPasswordEncoder.encode(userDTO.getPassword())));
    userRepository.save(user);
    log.info("User with: " + user.getUsername() + " save now in Db");
    Set<GrantedAuthority> authorities = new HashSet<>();
    authorities.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
    Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return true;
  }

  /**
   * Update user
   *
   * @param userDTO - user
   */
  @Override
  @Transactional
  public void update(final @NonNull UserDTO userDTO) {
    final User user = userRepository.findById(userDTO.getId())
        .orElseThrow(()->new EntityNotFoundException("Entity with given id not exist"));
    user.setUserFirstName(userDTO.getUserFirstName());
    user.setUserLastName(userDTO.getUserLastName());
    user.setUserBirthday(userDTO.getUserBirthday());
    user.setUserEmail(userDTO.getUserEmail());
    log.info("User with: " + user.getUsername() + " updated now");
  }

  /**
   * Update user password
   *
   * @param newPassword - new password
   * @param userName - username
   */
  @Override
  @Transactional
  public void updatePassword(String newPassword, String userName){
    User userDb = userRepository.findByUsername(userName);
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = bCryptPasswordEncoder.encode(newPassword);
    userDb.setPassword(encodedPassword);
    userRepository.save(userDb);
    log.info("User with: " + userDb.getUsername() + " now update password");
  }

  /**
   * Update user address
   *
   * @param userAddressDTO - user address
   */
  @Override
  @Transactional
  public void updateAddress(UserAddressDTO userAddressDTO){
    UserAddress dB = addressRepository.findById(userAddressDTO.getId())
        .orElseThrow(()-> new EntityNotFoundException("Address not exist"));
    dB.setUserCountry(userAddressDTO.getUserCountry());
    dB.setUserCity(userAddressDTO.getUserCity());
    dB.setUserZip(userAddressDTO.getUserZip());
    dB.setUserStreet(userAddressDTO.getUserStreet());
    dB.setUserHouse(userAddressDTO.getUserHouse());
    dB.setUserApartment(userAddressDTO.getUserApartment());
    log.info("User with: " + dB.getUser().getUsername() + " now update address");
  }

  /**
   * Find userDTO
   *
   * @param id - id
   * @return userDTO
   */
  @Override
  @Transactional(readOnly = true)
  public UserDTO findById(long id) {
    return mapper.toDto(findUserById(id));
  }

  /**
   * Find user
   *
   * @param id - id
   * @return user
   */
  private User findUserById(long id){
    return userRepository.findById(id);
  }

}
