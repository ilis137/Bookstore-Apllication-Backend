package com.bookstoreapplication.bookstoreapplication.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@Data
@AllArgsConstructor(staticName = "Build")
@NoArgsConstructor
public class SellerDTO {
  private int id;
  private String name;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private PagingDTO paging;
}
