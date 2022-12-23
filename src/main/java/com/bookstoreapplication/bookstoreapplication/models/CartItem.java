package com.bookstoreapplication.bookstoreapplication.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cartItem")
@Data
@AllArgsConstructor(staticName = "Build")
@NoArgsConstructor
public class CartItem {
  @Id
  private int cartItemId;
  @ManyToOne(fetch = FetchType.EAGER )
  @JoinColumn(name="book_id")
  private Book book;
  private int quantity;
}
