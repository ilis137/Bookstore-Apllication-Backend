package com.bookstoreapplication.bookstoreapplication.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstoreapplication.bookstoreapplication.models.Role;
import com.bookstoreapplication.bookstoreapplication.models.RoleEnum;

@Repository
public interface roleRepository extends JpaRepository<Role,Integer>{
  Optional<Role> findByName(RoleEnum name);
}
