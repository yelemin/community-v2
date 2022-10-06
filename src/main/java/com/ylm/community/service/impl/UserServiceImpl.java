package com.ylm.community.service.impl;

import com.ylm.community.mapper.UserMapper;
import com.ylm.community.model.entity.UserEntity;
import com.ylm.community.model.input.AddUserInfoInput;
import com.ylm.community.model.input.GetUserInfoInput;
import com.ylm.community.service.UserService;
import com.ylm.community.utils.AssertUtil;
import com.ylm.community.utils.EncodeUtils;
import com.ylm.community.utils.RadixParserUtils;
import com.ylm.community.utils.SnowflakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author flyingwhale
 * @date 2022/10/4
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private SnowflakeUtils snowflakeUtils;
    @Resource
    private UserMapper userMapper;

    @Override
    public UserEntity get(GetUserInfoInput input) {
        AssertUtil.notNull(input);
        if (StringUtils.isNotBlank(input.getUsername())){
            return userMapper.getByUsername(input.getUsername());
        }
        return null;
    }

    @Override
    public void add(AddUserInfoInput input) {
        AssertUtil.notNull(input);

        String salt = generateSalt();
        String uid = generateUid();

        UserEntity entity = new UserEntity();
        entity.setUsername(input.getUsername());
        entity.setNickname(input.getNickname());
        // 加密密码
        entity.setPassword(
                encodeContent(salt, input.getPassword())
        );
        entity.setSalt(salt);
        //todo
        entity.setEncryptedMobile(StringUtils.EMPTY);
        entity.setMobileUid(StringUtils.EMPTY);
        entity.setUid(uid);
        try {
            userMapper.add(entity);
        }catch (Exception e){
            log.error("userMapper.add failed！", e);
        }
    }

    private String generateUid(){
        return RadixParserUtils.encode(snowflakeUtils.nextId(), 12);
    }

    private String generateEncodeContentUid(String uniqueKey, String content){
        return EncodeUtils.Md5.md5Hex(uniqueKey + "_" + content);
    }

    private String encodeContent(String salt, String content) {
        return EncodeUtils.AES.encodeStringContentAndConvertBase64(content, salt);
    }

    @Override
    public String decodeContent(String salt, String content) {
        return EncodeUtils.AES.decodeStringContent(content, salt);
    }

    private String generateSalt() {
        return RandomStringUtils.random(16, true, true);
    }

}
