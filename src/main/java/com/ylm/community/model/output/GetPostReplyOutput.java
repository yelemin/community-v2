package com.ylm.community.model.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author flyingwhale
 * @date 2022/10/8
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPostReplyOutput {

    private String content;

    private String postUid;

    private String replyUserUid;

    private String replyUserNickname;

    private Integer likeCount;

    private Integer replyCount;

    private String createTime;

}
