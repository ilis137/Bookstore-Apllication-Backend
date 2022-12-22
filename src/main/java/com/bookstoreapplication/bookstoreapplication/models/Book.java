package com.bookstoreapplication.bookstoreapplication.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

/**
 * Book entity contains book info
 **/
@Entity
@Table(name="book")
@Getter
@Setter
@AllArgsConstructor(staticName = "Build")
@NoArgsConstructor
public class Book {

    @Id
    private int bookId;
    private String bookName;
    private String author;
    private int bookPrice;
    private int quantity;
    @Lob
    private String bookImage;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
   // @JsonBackReference
   @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User seller;

}

