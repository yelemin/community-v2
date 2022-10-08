package com.ylm.community.model.input;

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
public class GetPostDetailInput {

    private String postUid;

    private String userUid;

    private Integer pageNo;

    private Integer pageSize;

}
