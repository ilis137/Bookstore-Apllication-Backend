package com.bookstoreapplication.bookstoreapplication.models;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "orderitem")
@Getter
@Setter
@AllArgsConstructor(staticName = "Build")
@NoArgsConstructor
public class OrderItem {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private int orderItemId;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id")
  private Book book;
  private int quantity;

  public OrderItem(Book book, int quantity) {
    this.book = book;
    this.quantity = quantity;
  }

}
