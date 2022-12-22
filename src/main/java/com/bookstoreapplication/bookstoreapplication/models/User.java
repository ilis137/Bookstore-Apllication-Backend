package com.bookstoreapplication.bookstoreapplication.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "UserData", uniqueConstraints = {
    @UniqueConstraint(columnNames = "emailId"),
    @UniqueConstraint(columnNames = "username")
})
@Getter
@Setter
@AllArgsConstructor(staticName = "Build")
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int userId;
  @NotNull(message = "UserName should not be null")
  @Size(max = 20)
  private String username;

  @NotNull(message = "mobile no shouldn't be null")
  @Pattern(regexp = "^\\d{10}$", message = "Invalid mobileNo entered")
  private String mobileNo;
  @NotBlank
  @Email(message = "Email address entered is not valid")
  @Size(max = 20)
  private String emailId;

  @NotBlank
  @Size(max = 120)
  @JsonProperty(access = Access.WRITE_ONLY)
  private String password;

  @NotNull
  private String city;

  @NotNull
  private String state;

  @NotNull
  private int zipCode;
  @NotNull
  private String address;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private Set<CartItem> cartItems = new HashSet<>();

  
  @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "seller")
  @JsonIgnore
  private Set<Book> BookStock=new HashSet<>();
}
