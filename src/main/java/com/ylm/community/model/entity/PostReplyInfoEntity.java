package com.ylm.community.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author flyingwhale
 * @date 2022/10/6
 */
@Data
public class PostReplyInfoEntity {

    private Long id;

    private String uid;

    private String postUid;

    private String replyFloorUid;

    private String parentReplyFloorUid;

    private String content;

    private String userUid;

    private Integer likeCount;

    private Integer isDelete;

    private Date createTime;

    private Date updateTime;

}
