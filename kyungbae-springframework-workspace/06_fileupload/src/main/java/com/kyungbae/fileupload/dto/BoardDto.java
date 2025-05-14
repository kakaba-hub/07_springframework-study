package com.kyungbae.fileupload.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class BoardDto {
    private int boardNo;
    private String boardTitle;
    private String boardContent;
}
