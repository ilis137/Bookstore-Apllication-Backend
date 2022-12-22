package com.bookstoreapplication.bookstoreapplication.Services;

import com.bookstoreapplication.bookstoreapplication.DTO.UserRegistrationDTO;
import com.bookstoreapplication.bookstoreapplication.Exception.UserNotFoundException;
import com.bookstoreapplication.bookstoreapplication.models.Book;
import com.bookstoreapplication.bookstoreapplication.models.User;

public interface IUserService {
  User addUser(UserRegistrationDTO userRegDTO);

  int getUserIdByUserName(String userName) throws UserNotFoundException;

  boolean existsByEmail(String email) ;

  User findByUserId(int id) throws UserNotFoundException;

  void addBookToUser(Book book, User seller);

  User updateUser(User user);
}
