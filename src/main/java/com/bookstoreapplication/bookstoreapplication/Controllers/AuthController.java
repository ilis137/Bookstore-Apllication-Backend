package com.bookstoreapplication.bookstoreapplication.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstoreapplication.bookstoreapplication.DTO.LoginDTO;
import com.bookstoreapplication.bookstoreapplication.DTO.ResponseDTO;
import com.bookstoreapplication.bookstoreapplication.DTO.UserRegistrationDTO;
import com.bookstoreapplication.bookstoreapplication.Repository.RoleRepository;
import com.bookstoreapplication.bookstoreapplication.Repository.UserRepository;
import com.bookstoreapplication.bookstoreapplication.Services.IUserService;
import com.bookstoreapplication.bookstoreapplication.Util.JWTUtil;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
	AuthenticationManager authenticationManager;

  @Autowired
  private IUserService userService;

  @Autowired
  JWTUtil jwtUtils;

  @PostMapping("/register")
  public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody UserRegistrationDTO userRegDTO){
    ResponseDTO responseDTO = ResponseDTO.Build("User registration successful", userService.addUser(userRegDTO));
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);

  }

  @PostMapping("/login")
  public ResponseEntity<ResponseDTO> loginUser(@Valid @RequestBody LoginDTO loginDTO){
    Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    
    ResponseDTO responseDTO = ResponseDTO.Build(jwt,userService.getUserIdByUserName(loginDTO.getUserName()));

    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }
}
