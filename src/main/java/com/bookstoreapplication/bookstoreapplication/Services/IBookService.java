package com.bookstoreapplication.bookstoreapplication.Services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.bookstoreapplication.bookstoreapplication.DTO.BookDTO;
import com.bookstoreapplication.bookstoreapplication.DTO.PagingDTO;
import com.bookstoreapplication.bookstoreapplication.Exception.BookNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Exception.UserNotFoundException;
import com.bookstoreapplication.bookstoreapplication.models.Book;


public interface IBookService {
  Book addBookStock(BookDTO bookDTO) throws UserNotFoundException;

  Book updateBookById(int id, BookDTO bookDTO) throws BookNotFoundException;

  boolean deleteBookById(int id) throws BookNotFoundException;

  List<Book> getAllBooks(PagingDTO pagingDTO) throws BookNotFoundException;

  Book getBook(int id) throws BookNotFoundException;
  
 List<Book> getAllBooksBySellerUserId(int seller_id,PagingDTO pagingDTO) throws BookNotFoundException;
}
