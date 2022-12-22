package com.bookstoreapplication.bookstoreapplication.Services;

import java.util.List;
import java.util.Set;

import com.bookstoreapplication.bookstoreapplication.Exception.BookNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Exception.CartItemNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Exception.UserNotFoundException;
import com.bookstoreapplication.bookstoreapplication.models.CartItem;
import com.bookstoreapplication.bookstoreapplication.models.User;

public interface ICartService {
  public Set<CartItem> addToCart(int user_id, int book_id) throws BookNotFoundException, UserNotFoundException,CartItemNotFoundException;

  public Set<CartItem> removeFromCart(int cartitem_id,int user_id) throws UserNotFoundException,CartItemNotFoundException ;

  public Set<CartItem> emptyCart( int user_id) throws UserNotFoundException,CartItemNotFoundException;

  public Set<CartItem> getCart(int user_id) throws UserNotFoundException,CartItemNotFoundException;
}
