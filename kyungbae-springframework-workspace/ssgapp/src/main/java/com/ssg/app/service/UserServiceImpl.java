package com.ssg.app.service;

import com.ssg.app.dao.UserMapper;
import com.ssg.app.dto.UserDto;
import com.ssg.app.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final SqlSessionTemplate sqlSession;
    private final FileUtil fileUtil;

    @Override
    public int registUser(UserDto user) {
        return sqlSession.getMapper(UserMapper.class).insertUser(user);
    }

    @Override
    public int getUserCount(String checkId) {
        return sqlSession.getMapper(UserMapper.class).selectUserCountById(checkId);
    }

    @Override
    public UserDto getUser(UserDto user) {
        UserDto selectedUser = sqlSession.getMapper(UserMapper.class).selectUserById(user.getUserId());

        // 로그인 실패 케이스
        if (selectedUser == null || !user.getUserPwd().equals(selectedUser.getUserPwd())) {
            return null;
        }

        return selectedUser;
    }

    @Override
    public int modifyUserProfile(UserDto loginUser, MultipartFile uploadFile) {

        Map<String, String> map = fileUtil.fileupload("profile", uploadFile);
        String profileURL = map.get("filePath") + "/" + map.get("filesystemName");

        loginUser.setProfileURL(profileURL);
        return sqlSession.getMapper(UserMapper.class).updateProfileImg(loginUser);
    }
}
