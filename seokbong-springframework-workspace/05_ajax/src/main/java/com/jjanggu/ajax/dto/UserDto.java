package com.jjanggu.ajax.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

    private String id;
    private String pwd;
    private String name;
    private int age;
}
