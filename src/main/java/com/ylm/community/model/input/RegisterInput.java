package com.ylm.community.model.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author flyingwhale
 * @date 2022/10/5
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterInput {

    private String username;

    private String password;

    private String confirmedPassword;

    private String nickname;

}
