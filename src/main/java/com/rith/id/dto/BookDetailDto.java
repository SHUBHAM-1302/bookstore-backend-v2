package com.rith.id.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailDto {

    private Long bookId;

    private String title;

    private String auther;

    private Double rate;

    private Boolean isSold;

    private String description;

}
