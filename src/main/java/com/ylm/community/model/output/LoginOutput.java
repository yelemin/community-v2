package com.ylm.community.model.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author flyingwhale
 * @date 2022/10/4
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginOutput {

    private String nickname;

    private String username;

    private String token;

}
