package com.bookstoreapplication.bookstoreapplication.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
   
   
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User seller;
    
    public Book(String bookName, String author, int bookPrice, int quantity, String bookImage, User seller) {
        this.bookName = bookName;
        this.author = author;
        this.bookPrice = bookPrice;
        this.quantity = quantity;
        this.bookImage = bookImage;
        this.seller = seller;
    }
}
