package com.bookstoreapplication.bookstoreapplication.Services;

import java.util.List;
import java.util.Set;

import com.bookstoreapplication.bookstoreapplication.DTO.CartDTO;
import com.bookstoreapplication.bookstoreapplication.Exception.BookNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Exception.CartItemNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Exception.UserNotFoundException;
import com.bookstoreapplication.bookstoreapplication.models.CartItem;
import com.bookstoreapplication.bookstoreapplication.models.User;

public interface ICartService {
  public List<CartDTO> addToCart(int user_id, int book_id)
      throws BookNotFoundException, UserNotFoundException, CartItemNotFoundException;

  public List<CartDTO> removeFromCart(int cartitem_id, int user_id)
      throws UserNotFoundException, CartItemNotFoundException;

  public List<CartDTO> emptyCart(int user_id) throws UserNotFoundException, CartItemNotFoundException;

  public List<CartDTO> getCart(int user_id) throws UserNotFoundException, CartItemNotFoundException;

  public Set<CartItem> getCartEntity(int user_id) throws UserNotFoundException, CartItemNotFoundException;

}
