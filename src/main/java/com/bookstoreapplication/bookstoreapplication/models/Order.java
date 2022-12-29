package com.bookstoreapplication.bookstoreapplication.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.*;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor(staticName = "Build")
@NoArgsConstructor
public class Order {
 
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private int orderId;
  @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
  @JoinColumn(name = "user_id")
  private User user;
  private long totalPrice;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "order_id")
  private Set<OrderItem> orderItems = new HashSet<>();
  public Order(User user, long totalPrice, Set<OrderItem> orderItems) {
    this.user = user;
    this.totalPrice = totalPrice;
    this.orderItems = orderItems;
  }

}
