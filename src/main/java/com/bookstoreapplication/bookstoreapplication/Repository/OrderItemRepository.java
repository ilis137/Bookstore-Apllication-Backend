package com.bookstoreapplication.bookstoreapplication.Repository;

import java.util.List;

import javax.transaction.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstoreapplication.bookstoreapplication.models.Book;
import com.bookstoreapplication.bookstoreapplication.models.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>{
  @Transactional
  @Modifying
  @Query(value="select * from order_item where book_id=?1",nativeQuery=true)
  List<Book> checkForBook(int id);

  
} 