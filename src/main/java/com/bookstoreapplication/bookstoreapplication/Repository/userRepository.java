package com.bookstoreapplication.bookstoreapplication.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstoreapplication.bookstoreapplication.models.User;

@Repository
public interface userRepository extends JpaRepository<User,Integer>{
  Optional<User> findByusername(String username);
  Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
}
