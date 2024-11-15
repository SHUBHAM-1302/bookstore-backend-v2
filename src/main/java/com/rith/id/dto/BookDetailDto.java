package com.rith.id.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailDto extends BookDto{

    private String status;

    private String summary;

}
