package com.ylm.community.model.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author flyingwhale
 * @date 2022/10/6
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPostReplyInput {

    private String postUid;

}
