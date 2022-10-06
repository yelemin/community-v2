package com.ylm.community.mapper;

import com.ylm.community.model.entity.UserEntity;

/**
 * @author flyingwhale
 * @date 2022/10/4
 */
public interface UserMapper {

    UserEntity getByUsername(String username);

    void add(UserEntity entity);

}
