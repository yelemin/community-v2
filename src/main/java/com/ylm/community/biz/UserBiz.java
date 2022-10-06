package com.ylm.community.biz;

import com.ylm.community.model.input.LoginInput;
import com.ylm.community.model.input.RegisterInput;
import com.ylm.community.model.output.LoginOutput;

/**
 * @author flyingwhale
 * @date 2022/10/4
 */
public interface UserBiz {


    LoginOutput login(LoginInput input);

    void register(RegisterInput input);

}
