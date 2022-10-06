package com.ylm.community.biz.impl;

import com.ylm.community.biz.UserBiz;
import com.ylm.community.constant.CommonConstants;
import com.ylm.community.enums.ErrorCode;
import com.ylm.community.model.base.CommunityException;
import com.ylm.community.model.entity.UserEntity;
import com.ylm.community.model.input.AddUserInfoInput;
import com.ylm.community.model.input.GetUserInfoInput;
import com.ylm.community.model.input.LoginInput;
import com.ylm.community.model.input.RegisterInput;
import com.ylm.community.model.output.LoginOutput;
import com.ylm.community.service.UserService;
import com.ylm.community.utils.AssertUtil;
import com.ylm.community.utils.BeanUtils;
import com.ylm.community.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author flyingwhale
 * @date 2022/10/4
 */
@Slf4j
@Service
public class UserBizImpl implements UserBiz {

    @Resource
    private UserService userService;

    @Override
    public LoginOutput login(LoginInput input) {
        AssertUtil.notNull(input);
        AssertUtil.notBlank(input.getUsername(), "用户名不能为空！");
        AssertUtil.notBlank(input.getPassword(), "密码不能为空！");

        UserEntity entity = userService.get(
                GetUserInfoInput.builder()
                        .username(input.getUsername()).build()
        );
        if (Objects.isNull(entity)) {
            throw new CommunityException(ErrorCode.SYSTEM_PARAM_ERROR, "用户名或密码错误！");
        }
        String storedPassword = userService.decodeContent(entity.getSalt(), entity.getPassword());
        if (!storedPassword.equals(input.getPassword())) {
            throw new CommunityException(ErrorCode.SYSTEM_PARAM_ERROR, "用户名或密码错误！");
        }

        return buildLoginOutput(entity);
    }

    @Override
    public void register(RegisterInput input) {
        AssertUtil.notNull(input);
        AssertUtil.notBlank(input.getUsername(), "用户名不能为空！");
        AssertUtil.notBlank(input.getPassword(), "密码不能为空！");
        AssertUtil.notBlank(input.getNickname(), "昵称不能为空！");

        UserEntity entity = userService.get(
                GetUserInfoInput.builder()
                        .username(input.getUsername()).build()
        );
        if (Objects.nonNull(entity)) {
            throw new CommunityException(ErrorCode.SYSTEM_PARAM_ERROR, "用户名已注册！");
        }
        // todo
        if (!StringUtils.equals(input.getPassword(), input.getConfirmedPassword())) {
            throw new CommunityException(ErrorCode.SYSTEM_PARAM_ERROR, "确认密码与密码不相同！");
        }

        userService.add(
                AddUserInfoInput.builder()
                .username(input.getUsername())
                .nickname(input.getNickname())
                .password(input.getPassword()).build()
        );
    }

    private LoginOutput buildLoginOutput(UserEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        return BeanUtils.copyBean(entity, LoginOutput::new, (from, to) -> {
            // 生成token
            HashMap<String, String> paramsMap = new HashMap<>();
            long expiresInTime = CommonConstants.AUTH_TOKEN_EXPIRES_IN * 1000L;
            long currentTimeMillis = System.currentTimeMillis();
            paramsMap.put("username", from.getUsername());
            paramsMap.put("effectTime", String.valueOf(currentTimeMillis));
            paramsMap.put("expirationTime", String.valueOf(currentTimeMillis + expiresInTime));
            String token = TokenUtils.makeToken(paramsMap, CommonConstants.ACCOUNT_AES_KEY, CommonConstants.ACCOUNT_SIGN_KEY);
            to.setToken(token);
        });
    }

}
