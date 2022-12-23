package com.bookstoreapplication.bookstoreapplication.DTO;

import java.util.ArrayList;
import java.util.List;

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
 
  private List<OrderItem> orderItems = new ArrayList<>();
}
