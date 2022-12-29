package com.bookstoreapplication.bookstoreapplication.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private int cartItemId;
  @ManyToOne(fetch = FetchType.EAGER )
  @JoinColumn(name="book_id")
  private Book book;
  
  private int quantity;

  public CartItem(Book book, int quantity) {
    this.book = book;
    this.quantity = quantity;
  }
}
