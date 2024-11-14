package com.rith.id.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "book")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String title;

    private String author;

    private Double price;

    private Boolean isSold;

    private String summary;

}
