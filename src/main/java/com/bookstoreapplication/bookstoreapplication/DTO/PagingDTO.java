package com.bookstoreapplication.bookstoreapplication.DTO;

import lombok.*;

@Data
@AllArgsConstructor(staticName = "Build")
@NoArgsConstructor
public class PagingDTO {
  int startPage;
  int size;
}
