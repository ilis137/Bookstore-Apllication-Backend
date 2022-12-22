package com.bookstoreapplication.bookstoreapplication.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookstoreapplication.bookstoreapplication.Repository.RoleRepository;
import com.bookstoreapplication.bookstoreapplication.Repository.UserRepository;
import com.bookstoreapplication.bookstoreapplication.models.Role;
import com.bookstoreapplication.bookstoreapplication.models.User;

@Service
public class CustomUserDetailService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
  
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
        getGrantedAuthorities(user.getRoles()));
  }



   

  private List<GrantedAuthority> getGrantedAuthorities(Collection<Role> roles) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (Role role :roles) {
      authorities.add(new SimpleGrantedAuthority(role.getName().name()));
    }
    return authorities;
  }
}

