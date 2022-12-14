package com.bookstoreapplication.bookstoreapplication.Services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookstoreapplication.bookstoreapplication.DTO.UserRegistrationDTO;
import com.bookstoreapplication.bookstoreapplication.Repository.RoleRepository;
import com.bookstoreapplication.bookstoreapplication.Repository.UserRepository;
import com.bookstoreapplication.bookstoreapplication.models.Role;
import com.bookstoreapplication.bookstoreapplication.models.RoleEnum;
import com.bookstoreapplication.bookstoreapplication.models.User;

@Service
public class UserService implements IUserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Override
  public User addUser(UserRegistrationDTO userRegDTO) {
    User user = User.Build(userRepository.findAll().size() + 1, userRegDTO.getName(), userRegDTO.getMobileNo(),
        userRegDTO.getEmailId(), encoder.encode(userRegDTO.getPassword()), userRegDTO.getCity(), userRegDTO.getState(),
        userRegDTO.getZipCode(), userRegDTO.getAddress(), null);
    Set<String> strRoles = userRegDTO.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role admin is not found."));
            roles.add(adminRole);

            break;
          case "seller":
            Role modRole = roleRepository.findByName(RoleEnum.ROLE_SELLER)
                .orElseThrow(() -> new RuntimeException("Error: Role seller is not found."));
            roles.add(modRole);

            break;
          default:
            Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role user is not found."));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);

    return userRepository.save(user);
  }

  @Override
  public int getUserIdByUserName(String userName) {
    int userId = userRepository.findByUsername(userName).orElseThrow(()->
      new RuntimeException("Error: User is not found.")
    ).getUserId();
    return userId;
  }

}
