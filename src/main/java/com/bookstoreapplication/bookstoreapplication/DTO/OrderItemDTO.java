package com.bookstoreapplication.bookstoreapplication.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
  private int orderItemId;
  private BookResponseDTO bookData;
  private int quantity;
}
