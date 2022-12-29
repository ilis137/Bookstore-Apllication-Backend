package com.bookstoreapplication.bookstoreapplication.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bookstoreapplication.bookstoreapplication.DTO.BookDTO;
import com.bookstoreapplication.bookstoreapplication.DTO.BookResponseDTO;
import com.bookstoreapplication.bookstoreapplication.DTO.PagingDTO;
import com.bookstoreapplication.bookstoreapplication.Exception.BookNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Exception.UserNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Repository.IBookRepository;
import com.bookstoreapplication.bookstoreapplication.models.Book;
import com.bookstoreapplication.bookstoreapplication.models.User;

@Service
public class BookService implements IBookService {
  @Autowired
  private IBookRepository bookRepository;

  @Autowired
  IUserService userService;

  @Autowired
  ModelMapper modelMapper;

  // @Autowired
  // IOrderService orderService;

  @Override
  public BookResponseDTO addBookStock(BookDTO bookDTO) throws UserNotFoundException {
    User seller = userService.findByUserId(bookDTO.getSeller_id());
    Book book = new Book(bookDTO.getBookName(), bookDTO.getAuthor(), bookDTO.getBookPrice(), bookDTO.getQuantity(),
        bookDTO.getBookImage(), seller);
    // System.out.println(book.getBookId());
    userService.addBookToUser(book, seller);
    return toBookResponseDTO(bookRepository.save(book));
  }
 
  @Override
  public BookResponseDTO updateBookById(int id, BookDTO bookDTO) throws BookNotFoundException {
    Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
    book.setBookName(bookDTO.getBookName());
    book.setAuthor(bookDTO.getAuthor());
    book.setBookPrice(bookDTO.getBookPrice());
    book.setQuantity(bookDTO.getQuantity());
    book.setBookImage(bookDTO.getBookImage());
    return toBookResponseDTO(bookRepository.save(book));
  }

  @Override
  public boolean deleteBookById(int id) throws BookNotFoundException {
    // if(!this.getBook(id).getOrderitems().isEmpty()){
    // throw new BookNotFoundException("Unable to delete Book.it has been ordered");
    // }
    try {
      bookRepository.deleteById(id);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new BookNotFoundException("Book not found");
    }

    return true;
  }

  @Override
  public List<BookResponseDTO> getAllBooks(PagingDTO pagingDTO) throws BookNotFoundException {
    Pageable pageable = PageRequest.of(pagingDTO.getStartPage(), pagingDTO.getSize());
    try {
      return bookRepository.findAll(pageable).toList().stream().map(book->toBookResponseDTO(book)).collect(Collectors.toList());
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new BookNotFoundException("Books not found");
    }

  }

  @Override
  public BookResponseDTO getBook(int id) throws BookNotFoundException {
    try {
      return toBookResponseDTO(bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Error: Book is not found.")));
    } catch (Exception e) {
      throw new BookNotFoundException("Book not found");
    }

  }

  public Book getBookEntity(int id) throws BookNotFoundException {
    try {
      return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Error: Book is not found."));
    } catch (Exception e) {
      throw new BookNotFoundException("Book not found");
    }

  }

  @Override
  public List<BookResponseDTO> getAllBooksBySellerUserId(int seller_id, PagingDTO pagingDTO) throws BookNotFoundException {
    Pageable pageable = PageRequest.of(pagingDTO.getStartPage(), pagingDTO.getSize());

    try {
      return bookRepository.getAllBooksBySellerUserId(seller_id, pageable).stream().map(book->toBookResponseDTO(book)).collect(Collectors.toList());
    } catch (Exception e) {
      throw new BookNotFoundException("Books not found");
    }

  }

  @Override
  public void removeStock(Book book, int quantity) {
    int newQuantity = book.getQuantity() - quantity;
    book.setQuantity(newQuantity);
    bookRepository.save(book);
  }

  @Override
  public void addStock(Book book, int quantity) {
    int newQuantity = book.getQuantity() + quantity;
    book.setQuantity(newQuantity);
    bookRepository.save(book);
  }

  public BookResponseDTO toBookResponseDTO(Book book) {
   // modelMapper.getConfiguration().setAmbiguityIgnored(true);
    modelMapper.typeMap(Book.class, BookResponseDTO.class).addMappings(
    mapper->{
      mapper.map(src -> src.getSeller().getUserId(), BookResponseDTO::setSeller_id);
      mapper.map(src -> src.getSeller().getUsername(), BookResponseDTO::setSeller_name);
    });
    return modelMapper.map(book, BookResponseDTO.class);
  }

 
}
