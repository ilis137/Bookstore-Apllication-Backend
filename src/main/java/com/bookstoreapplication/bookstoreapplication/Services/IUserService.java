package com.bookstoreapplication.bookstoreapplication.Services;

import com.bookstoreapplication.bookstoreapplication.DTO.UserRegistrationDTO;
import com.bookstoreapplication.bookstoreapplication.models.User;

public interface IUserService {
  User addUser(UserRegistrationDTO userRegDTO);
 int getUserIdByUserName(String userName); 

}
