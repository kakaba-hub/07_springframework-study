package com.kyungbae.ajax.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDto {
    private String id;
    private String pw;
    private String name;
    private int age;
}
