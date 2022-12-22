package com.bookstoreapplication.bookstoreapplication.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookstoreapplication.bookstoreapplication.models.Book;

public interface IBookRepository extends JpaRepository<Book,Integer>{
  List<Book> getAllBooksBySellerUserId(int seller_id,Pageable pageable);
}
