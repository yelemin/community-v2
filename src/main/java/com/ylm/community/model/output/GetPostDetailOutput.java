package com.ylm.community.model.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author flyingwhale
 * @date 2022/10/8
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPostDetailOutput {

    private String title;

    private String content;

    private String postUserUid;

    private String postUserNickname;

    private Integer likeCount;

    private Integer replyCount;

    private String createTime;

    private List<GetPostReplyOutput> replyList;

}
