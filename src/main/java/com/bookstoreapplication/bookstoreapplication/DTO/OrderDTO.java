package com.bookstoreapplication.bookstoreapplication.DTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bookstoreapplication.bookstoreapplication.models.OrderItem;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
  private int orderId;
  private int userId;
  private String userName;
  private String mobileNo;
  private String emailId;
  private String address;
  private String city;
  private String state;
  private long totalPrice;
 
  private Set<OrderItemDTO> orderItems = new HashSet<>();
}
