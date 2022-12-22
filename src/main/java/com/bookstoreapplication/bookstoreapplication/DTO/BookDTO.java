package com.bookstoreapplication.bookstoreapplication.DTO;

import com.bookstoreapplication.bookstoreapplication.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

  private int bookId;
  private String bookName;
  private String author;
  private int bookPrice;
  private int quantity;
  private String bookImage;
  private int seller_id;
}
