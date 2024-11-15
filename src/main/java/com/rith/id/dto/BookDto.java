package com.rith.id.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long bookId;

    private String title;

    private String author;

    private String price;
}
