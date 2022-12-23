package com.bookstoreapplication.bookstoreapplication.models;

import javax.persistence.Id;
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
public class OrderItem {
  @Id 
  private int orderItemId;
  @ManyToOne(fetch = FetchType.EAGER )
  @JoinColumn(name = "book_id")
  private Book book;
  private int quantity;
}
