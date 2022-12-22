package com.bookstoreapplication.bookstoreapplication.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.PageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.bookstoreapplication.bookstoreapplication.DTO.BookDTO;
import com.bookstoreapplication.bookstoreapplication.DTO.PagingDTO;
import com.bookstoreapplication.bookstoreapplication.DTO.ResponseDTO;
import com.bookstoreapplication.bookstoreapplication.DTO.SellerDTO;
import com.bookstoreapplication.bookstoreapplication.Exception.BookNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Exception.UserNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Services.IBookService;
import com.bookstoreapplication.bookstoreapplication.models.Book;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/book")
public class BookController {

  @Autowired
  IBookService bookService;

  @PostMapping("/add")
  @PreAuthorize("hasRole('ROLE_SELLER')")
  public ResponseEntity<ResponseDTO> addBook(@RequestBody @Valid BookDTO bookDTO) throws UserNotFoundException,MethodArgumentNotValidException {
    ResponseDTO responseDTO = ResponseDTO.Build("Added Book to store", bookService.addBookStock(bookDTO));
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  @PutMapping("/update/{bookId}")
  @PreAuthorize("hasRole('ROLE_SELLER')")
  public ResponseEntity<ResponseDTO> updateBook(@RequestBody @Valid BookDTO bookDTO,@PathVariable int bookId) throws BookNotFoundException,MethodArgumentNotValidException{
    Book book=bookService.updateBookById(bookId,bookDTO);
    ResponseDTO responseDTO = ResponseDTO.Build("Added Book to store", book);
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    
  }

  @DeleteMapping("/delete/{id}")
  @PreAuthorize("hasRole('ROLE_SELLER')")
  public ResponseEntity<ResponseDTO> deleteBook(@PathVariable int id) throws BookNotFoundException,MethodArgumentNotValidException{
    ResponseDTO responseDTO=ResponseDTO.Build("Deleted book from store",bookService.deleteBookById(id));
    return new ResponseEntity<>(responseDTO,HttpStatus.OK);
  }

  @GetMapping("/seller/view/all")
  @PreAuthorize("hasRole('ROLE_SELLER')")
  public ResponseEntity<ResponseDTO> viewAllBooksOFSeller(@RequestBody @Valid PagingDTO PagingDTO,@RequestParam("seller_id") int sellerId ) throws BookNotFoundException,MethodArgumentNotValidException{
    ResponseDTO responseDTO=ResponseDTO.Build("all books from store for seller "+sellerId,bookService.getAllBooksBySellerUserId(sellerId,PagingDTO));
    return new ResponseEntity<>(responseDTO,HttpStatus.OK);
  }

  @GetMapping("/view/all")
  @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_USER')")
  public ResponseEntity<ResponseDTO> viewAllBooks(@RequestBody @Valid PagingDTO PagingDTO) throws BookNotFoundException,MethodArgumentNotValidException{
    ResponseDTO responseDTO=ResponseDTO.Build("all books from store",bookService.getAllBooks(PagingDTO));
    return new ResponseEntity<>(responseDTO,HttpStatus.OK);
  }

  @GetMapping("/view/{id}")
  @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_USER')")
  public ResponseEntity<ResponseDTO> viewBook(@PathVariable int id) throws BookNotFoundException,MethodArgumentNotValidException{
    ResponseDTO responseDTO=ResponseDTO.Build("book with id"+id+" from store",bookService.getBook(id));
    return new ResponseEntity<>(responseDTO,HttpStatus.OK);
  }
}
