package com.ssg.app.dao;

import com.ssg.app.dto.UserDto;

public interface UserMapper {

    int insertUser(UserDto user);
    int selectUserCountById(String checkID);
    UserDto selectUserById(String userId);
    int updateProfileImg(UserDto user);

}
