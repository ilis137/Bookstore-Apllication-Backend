package com.bookstoreapplication.bookstoreapplication.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstoreapplication.bookstoreapplication.DTO.ResponseDTO;
import com.bookstoreapplication.bookstoreapplication.Exception.BookNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Exception.CartItemNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Exception.UserNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Services.CartService;
import com.bookstoreapplication.bookstoreapplication.Services.ICartService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
public class CartController {
  @Autowired
  ICartService cartService;

  @PostMapping("/add")
  @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_USER')")
  public ResponseEntity<ResponseDTO> addToCart(@RequestParam("book_id") int book_id, @RequestParam int user_id)
      throws UserNotFoundException, BookNotFoundException,MethodArgumentNotValidException ,CartItemNotFoundException{
    ResponseDTO responseDTO = ResponseDTO.Build("Added book with Id " + book_id + " to cart of user with Id " + user_id,
        cartService.addToCart(user_id,book_id));
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  @PostMapping("/remove")
  @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_USER')")
  public ResponseEntity<ResponseDTO> removeFromCart(@RequestParam("citem_id") int cartitem_id, @RequestParam("user_id") int user_id)
      throws UserNotFoundException, BookNotFoundException ,MethodArgumentNotValidException,CartItemNotFoundException{
    ResponseDTO responseDTO = ResponseDTO.Build("removed cart book with Id " + cartitem_id ,cartService.removeFromCart(cartitem_id,user_id));
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }
  @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_USER')")
  @PostMapping("/removeall")
  public ResponseEntity<ResponseDTO> emptyCart( @RequestParam("user_id") int user_id) throws UserNotFoundException,MethodArgumentNotValidException,CartItemNotFoundException {
      ResponseDTO responseDTO = ResponseDTO.Build("Cart is empty", cartService.emptyCart(user_id));
      return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_USER')")
  @GetMapping("/view")
  public ResponseEntity<ResponseDTO> getCart( @RequestParam("user_id") int user_id) throws UserNotFoundException,MethodArgumentNotValidException,CartItemNotFoundException {
      ResponseDTO responseDTO = ResponseDTO.Build("successfully fetched cart items for user "+user_id, cartService.getCart(user_id));
      return new ResponseEntity<>(responseDTO, HttpStatus.OK);
  }
}
