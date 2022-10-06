package com.ylm.community.service;

import com.ylm.community.mapper.UserMapper;
import com.ylm.community.model.entity.UserEntity;
import com.ylm.community.utils.SnowflakeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author flyingwhale
 * @date 2022/10/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Resource
    private SnowflakeUtils snowflakeUtils;

    @Test
    public void testSnowflake() {
        System.out.println(snowflakeUtils.nextId());
        System.out.println("ok");
    }

}
