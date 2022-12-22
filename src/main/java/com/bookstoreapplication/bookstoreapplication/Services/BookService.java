package com.bookstoreapplication.bookstoreapplication.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bookstoreapplication.bookstoreapplication.DTO.BookDTO;
import com.bookstoreapplication.bookstoreapplication.DTO.PagingDTO;
import com.bookstoreapplication.bookstoreapplication.Exception.BookNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Exception.UserNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Repository.IBookRepository;
import com.bookstoreapplication.bookstoreapplication.models.Book;
import com.bookstoreapplication.bookstoreapplication.models.User;

@Service
public class BookService implements IBookService{
  @Autowired 
  private IBookRepository bookRepository;

  @Autowired
  IUserService userService;

  @Override
  public Book addBookStock(BookDTO bookDTO) throws UserNotFoundException{
    User seller=userService.findByUserId(bookDTO.getSeller_id());
    Book book=Book.Build(bookRepository.findAll().size()+1,bookDTO.getBookName(),bookDTO.getAuthor(),bookDTO.getBookPrice(),bookDTO.getQuantity(),bookDTO.getBookImage(),seller);
    
    userService.addBookToUser(book,seller);
    return bookRepository.save(book);
  }

  @Override
  public Book updateBookById(int id,BookDTO bookDTO) throws BookNotFoundException{
    Book book=bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
    book.setBookName(bookDTO.getBookName());
    book.setAuthor(bookDTO.getAuthor());
    book.setBookPrice(bookDTO.getBookPrice());
    book.setQuantity(bookDTO.getQuantity());
    book.setBookImage(bookDTO.getBookImage());
    return bookRepository.save(book);
  }

  @Override
  public boolean deleteBookById(int id) throws BookNotFoundException{
    try {
      bookRepository.deleteById(id);
    } catch (Exception e) {
     throw new BookNotFoundException("Book not found");
    }
   
    return true;
  }

  @Override
  public List<Book> getAllBooks(PagingDTO pagingDTO) throws BookNotFoundException{
    Pageable pageable=PageRequest.of(pagingDTO.getStartPage(),pagingDTO.getSize());
    try {
      return bookRepository.findAll(pageable).toList();
    } catch (Exception e) {
      throw new BookNotFoundException("Books not found");
    }
   
     
  }

  @Override
  public Book getBook(int id) throws BookNotFoundException{
    try {
      return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Error: Book is not found."));
    } catch (Exception e) {
      throw new BookNotFoundException("Book not found");
    }
    
     
  }

  @Override
  public List<Book> getAllBooksBySellerUserId(int seller_id,PagingDTO pagingDTO) throws BookNotFoundException{
    Pageable pageable=PageRequest.of(pagingDTO.getStartPage(),pagingDTO.getSize());

    try {
      return bookRepository.getAllBooksBySellerUserId(seller_id,pageable);
    } catch (Exception e) {
      throw new BookNotFoundException("Books not found");
    }
   
  }
  
}
