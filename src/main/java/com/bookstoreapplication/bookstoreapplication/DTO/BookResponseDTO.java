package com.bookstoreapplication.bookstoreapplication.DTO;

import com.bookstoreapplication.bookstoreapplication.models.User;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {
  private int bookId;
  private String bookName;
  private String author;
  private int bookPrice;
  private int quantity;
  private String bookImage;
  private int seller_id;
  private String seller_name;
}
