package com.rith.id.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {
    String cause;
    String advise;
}
