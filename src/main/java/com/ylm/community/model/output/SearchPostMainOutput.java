package com.ylm.community.model.output;

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
public class SearchPostMainOutput {

    private String postUid;

    private String postUserUid;

    private String postUserNickName;

    private String title;

    private String content;

    private Integer likeCount;

    private Integer visitCount;

    private Integer replyCount;

}
