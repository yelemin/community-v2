package com.ylm.community.controller;

import com.ylm.community.biz.UserBiz;
import com.ylm.community.model.base.Response;
import com.ylm.community.model.input.LoginInput;
import com.ylm.community.model.input.RegisterInput;
import com.ylm.community.model.output.LoginOutput;
import com.ylm.community.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author flyingwhale
 * @date 2022/10/4
 */
@RestController
@RequestMapping(value = "/api/community/user")
@CrossOrigin
@Slf4j
public class UserController {

    @Resource
    private UserBiz userBiz;

    @PostMapping(path = "/login", name = "登录")
    public Response<LoginOutput> login(@RequestBody LoginInput input) {
        return ResponseUtil.makeSuccess(userBiz.login(input));
    }

    @PostMapping(path = "/register", name = "注册")
    public Response<Void> register(@RequestBody RegisterInput input) {
        userBiz.register(input);
        return ResponseUtil.makeSuccess(null);
    }

}
