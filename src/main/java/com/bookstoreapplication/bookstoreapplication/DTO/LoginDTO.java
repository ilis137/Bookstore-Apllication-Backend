package com.bookstoreapplication.bookstoreapplication.DTO;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
  /**
   * Data transfer object for login details
   * Generating getters,setters and constructors using lombook
   **/
  @NotNull(message = "UserName should not be null")
  private String userName;
  @NotBlank
  @Email(message = "Email address entered is not valid")
  private String emailId;
  @NotNull(message = "password should not be null")
  private String password;

}
