package com.bookstoreapplication.bookstoreapplication.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bookstoreapplication.bookstoreapplication.models.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem,Integer>{
  @Transactional
  @Modifying
  @Query(value="delete from cart_item where user_id=?1",nativeQuery=true)
  void deleteAllByUserId(int user_id);

}
