package com.rith.id.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;


@Entity
@Table(name = "books")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "author",nullable = false)
    private String author;

    @Column(name = "price",precision = 10,scale = 2,nullable = false)
    private BigDecimal price;

    @Column(name = "status",nullable = false,columnDefinition = "VARCHAR(50) DEFAULT 'available'")
    private String status;

    @Column(name = "summary",columnDefinition = "TEXT")
    private String summary;

}
