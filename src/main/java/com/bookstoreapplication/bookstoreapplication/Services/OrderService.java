package com.bookstoreapplication.bookstoreapplication.Services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstoreapplication.bookstoreapplication.DTO.OrderDTO;
import com.bookstoreapplication.bookstoreapplication.DTO.OrderItemDTO;
import com.bookstoreapplication.bookstoreapplication.Exception.CartItemNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Exception.UserNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Repository.OrderItemRepository;
import com.bookstoreapplication.bookstoreapplication.Repository.OrderRepository;
import com.bookstoreapplication.bookstoreapplication.models.Book;
import com.bookstoreapplication.bookstoreapplication.models.CartItem;
import com.bookstoreapplication.bookstoreapplication.models.Order;
import com.bookstoreapplication.bookstoreapplication.models.OrderItem;
import com.bookstoreapplication.bookstoreapplication.models.User;

@Service
public class OrderService implements IOrderService {

  @Autowired
  IUserService userService;

  @Autowired
  ICartService cartService;

  @Autowired
  IBookService bookService;

  @Autowired
  OrderItemRepository orderItemRepository;

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  ModelMapper modelMapper;

  public OrderDTO addOrder(int user_id) throws UserNotFoundException, CartItemNotFoundException {
    Set<CartItem> cart = cartService.getCartEntity(user_id);
    User user = userService.findByUserId(user_id);
    Set<OrderItem> orderItems = new HashSet<OrderItem>();
    int totalPrice = 0;
   
    for (CartItem cartItem : cart) {
      OrderItem orderItem = new OrderItem( cartItem.getBook(),cartItem.getQuantity());
      orderItems.add(orderItem);
      totalPrice += cartItem.getBook().getBookPrice();
     bookService.removeStock(cartItem.getBook(), cartItem.getQuantity());
    }
    Order order =new Order( user, totalPrice, orderItems);
    cartService.emptyCart(user_id);
    
    order = orderRepository.save(order);
    System.out.println(order.getOrderItems());
    return toOrderDTO(order);
  }

  

  @Override
  public void cancelOrder(int order_id) {

    for (OrderItem orderItem : this.getOrder(order_id).getOrderItems()) {
      bookService.addStock(orderItem.getBook(), orderItem.getQuantity());
    }
    orderRepository.deleteById(order_id);
  }

  @Override
  public Order getOrder(int order_id) {
    return orderRepository.findById(order_id).get();
  }

  @Override
  public List<Order> getAllOrder(int user_id) {
   return orderRepository.findByUserId(user_id);
  }

  // @Override
  // public List<Book> checkForBook(int id) {
  //    orderItemRepository.checkForBook(id);
  //   return  orderItemRepository.checkForBook(id);
  // }

  public OrderItemDTO toOrderItemDTO(OrderItem orderItem){
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
    modelMapper.typeMap(OrderItem.class, OrderItemDTO.class).addMappings(mapper -> {
      mapper.map(src -> bookService.toBookResponseDTO(src.getBook()), OrderItemDTO::setBookData);
    });
    return modelMapper.map(orderItem, OrderItemDTO.class);
  }
  public OrderDTO toOrderDTO(Order order) {
 
    modelMapper.getConfiguration().setAmbiguityIgnored(true);
    modelMapper.typeMap(Order.class, OrderDTO.class).addMappings(mapper -> {
      mapper.map(src -> src.getUser().getUserId(), OrderDTO::setUserId);
      mapper.map(src -> src.getUser().getEmailId(), OrderDTO::setEmailId);
      mapper.map(src -> src.getUser().getMobileNo(), OrderDTO::setMobileNo);
      mapper.map(src -> src.getUser().getAddress(), OrderDTO::setAddress);
      mapper.map(src -> src.getUser().getCity(), OrderDTO::setCity);
      mapper.map(src -> src.getUser().getState(), OrderDTO::setState);
      Set<OrderItemDTO> orderItemSet= order.getOrderItems().stream().map(OrderItem->toOrderItemDTO(OrderItem)).collect(Collectors.toSet());
      mapper.map(
        src -> {
          System.out.println(orderItemSet);
          return orderItemSet;}
      , OrderDTO::setOrderItems);

    });
    // OrderDTO orderDTO=modelMapper.map(order, OrderDTO.class);
    // orderDTO=orderDTO.getOrderItems().stream().map(orderitem->{

    //   toBookResponseDTO(orderitem.getBook());
    // }).collect(Collectors.toList());
    return modelMapper.map(order, OrderDTO.class);
  }
}
