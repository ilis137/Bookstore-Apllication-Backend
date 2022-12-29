package com.bookstoreapplication.bookstoreapplication.Services;

import java.util.List;

import com.bookstoreapplication.bookstoreapplication.DTO.OrderDTO;
import com.bookstoreapplication.bookstoreapplication.Exception.CartItemNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Exception.UserNotFoundException;
import com.bookstoreapplication.bookstoreapplication.models.Book;
import com.bookstoreapplication.bookstoreapplication.models.Order;

public interface IOrderService {

  public OrderDTO addOrder(int user_id) throws UserNotFoundException, CartItemNotFoundException;

  public void cancelOrder(int order_id);

  public Order getOrder(int order_id);

  public List<Order> getAllOrder(int user_id);

  //  public List<Book> checkForBook(int id); 
} 
