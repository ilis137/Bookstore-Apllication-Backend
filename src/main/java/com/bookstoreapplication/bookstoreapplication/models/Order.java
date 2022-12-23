package com.bookstoreapplication.bookstoreapplication.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="order")
@Getter
@Setter
@AllArgsConstructor(staticName = "Build")
@NoArgsConstructor
public class Order {
  @Id
  private int orderId;
  @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
  @JoinColumn(name="user_id")
  private User user;
  private long totalPrice;
  @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
  @JoinColumn(name = "order_id")
  private List<OrderItem> orderItems = new ArrayList<>();
}
