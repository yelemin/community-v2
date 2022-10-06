package com.ylm.community.service;

import com.ylm.community.model.entity.UserEntity;
import com.ylm.community.model.input.AddUserInfoInput;
import com.ylm.community.model.input.GetUserInfoInput;
import com.ylm.community.model.input.LoginInput;

/**
 * @author flyingwhale
 * @date 2022/10/4
 */
public interface UserService {

    String UNIQUE_KEY = "HClG4EZDop4sbD5X";

    UserEntity get(GetUserInfoInput input);

    void add(AddUserInfoInput input);

    String decodeContent(String salt, String content);

}
