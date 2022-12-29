package com.bookstoreapplication.bookstoreapplication.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bookstoreapplication.bookstoreapplication.models.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{
  @Transactional
  @Modifying
  @Query(value="select * from orders where user_id=?1",nativeQuery=true)
  List<Order> findByUserId(int user_id);
  
}
