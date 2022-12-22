package com.bookstoreapplication.bookstoreapplication.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstoreapplication.bookstoreapplication.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
  Optional<User> findByUsername(String username);
  Boolean existsByUsername(String username);
	Boolean existsByEmailId(String email);
  User findByUserId(int id);
  User findByEmailId(String username);
}
