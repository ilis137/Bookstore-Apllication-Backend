package com.bookstoreapplication.bookstoreapplication.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
  private int cartItemId;
  private BookResponseDTO bookData; 
  private int quantity;

}
