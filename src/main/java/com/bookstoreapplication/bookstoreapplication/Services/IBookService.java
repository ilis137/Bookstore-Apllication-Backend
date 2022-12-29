package com.bookstoreapplication.bookstoreapplication.Services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.bookstoreapplication.bookstoreapplication.DTO.BookDTO;
import com.bookstoreapplication.bookstoreapplication.DTO.BookResponseDTO;
import com.bookstoreapplication.bookstoreapplication.DTO.PagingDTO;
import com.bookstoreapplication.bookstoreapplication.Exception.BookNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Exception.UserNotFoundException;
import com.bookstoreapplication.bookstoreapplication.models.Book;

public interface IBookService {
  BookResponseDTO addBookStock(BookDTO bookDTO) throws UserNotFoundException;

  BookResponseDTO updateBookById(int id, BookDTO bookDTO) throws BookNotFoundException;

  boolean deleteBookById(int id) throws BookNotFoundException;

  List<BookResponseDTO> getAllBooks(PagingDTO pagingDTO) throws BookNotFoundException;

  BookResponseDTO getBook(int id) throws BookNotFoundException;

  List<BookResponseDTO> getAllBooksBySellerUserId(int seller_id, PagingDTO pagingDTO) throws BookNotFoundException;

  void removeStock(Book book, int quantity);

  void addStock(Book book, int quantity);

  Book getBookEntity(int book_id) throws BookNotFoundException;

  BookResponseDTO toBookResponseDTO(Book book);

}
