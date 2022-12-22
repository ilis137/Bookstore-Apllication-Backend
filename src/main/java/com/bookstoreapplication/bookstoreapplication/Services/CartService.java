
package com.bookstoreapplication.bookstoreapplication.Services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstoreapplication.bookstoreapplication.Exception.BookNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Exception.CartItemNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Exception.UserNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Repository.CartItemRepository;
import com.bookstoreapplication.bookstoreapplication.models.Book;
import com.bookstoreapplication.bookstoreapplication.models.CartItem;
import com.bookstoreapplication.bookstoreapplication.models.User;

@Service
public class CartService implements ICartService {
  @Autowired
  IUserService userService;

  @Autowired
  IBookService bookService;

  @Autowired
  CartItemRepository cartItemRepository;

  @Override
  public Set<CartItem> addToCart(int user_id, int book_id)
      throws BookNotFoundException, UserNotFoundException, CartItemNotFoundException {
    Book book;
    User user;

    book = bookService.getBook(book_id);
    user = userService.findByUserId(user_id);

    boolean present = false;
    Set<CartItem> cartItems = user.getCartItems();
    if (cartItems != null) {
      for (CartItem cartItem : cartItems) {
        if (cartItem.getBook().equals(book)) {
          cartItem.setQuantity(cartItem.getQuantity() + 1);
          present = true;
        }
      }
      if (!present) {
        CartItem cartitem = CartItem.Build(cartItemRepository.findAll().size() + 1, book, 1);
        cartItems.add(cartitem);

      }
    } else {
      CartItem cartitem = CartItem.Build(cartItemRepository.findAll().size() + 1, book, 1);
      cartItems = new HashSet<CartItem>();
      cartItems.add(cartitem);
    }

    return userService.updateUser(user).getCartItems();
  }

  @Override
  public Set<CartItem> removeFromCart(int cartitem_id, int user_id)
      throws UserNotFoundException, CartItemNotFoundException {
    CartItem cartItem;
    try {
      cartItem = cartItemRepository.findById(cartitem_id).get();
    } catch (Exception e) {
      throw new CartItemNotFoundException("cart item with id " + cartitem_id + " not found for user with id "+user_id);
    }

    if (cartItem.getQuantity() == 1) {
      try {
        cartItemRepository.deleteById(cartitem_id);
      } catch (Exception e) {
        throw new CartItemNotFoundException("cart item with id " + cartitem_id + " not found for user with id "+user_id);
      }

    } else {
      cartItem.setQuantity(cartItem.getQuantity() - 1);
      cartItemRepository.save(cartItem);
    }

    return userService.findByUserId(user_id).getCartItems();

  }

  @Override
  public Set<CartItem> emptyCart(int user_id) throws UserNotFoundException, CartItemNotFoundException {
    User user;
      try {
        user = userService.findByUserId(user_id);
      } catch (Exception e) {
        throw new UserNotFoundException("User with id "+user_id+" not found");
      }
    

    try {
      cartItemRepository.deleteAllByUserId(user_id);
    } catch (Exception e) {
      throw new CartItemNotFoundException("cart not found for user with id " + user_id);
    }

    return user.getCartItems();
  }

  @Override
  public Set<CartItem> getCart(int user_id) throws UserNotFoundException, CartItemNotFoundException {

    return userService.findByUserId(user_id).getCartItems();

  }

}
