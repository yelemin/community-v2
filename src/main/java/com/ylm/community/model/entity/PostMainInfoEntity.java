package com.ylm.community.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author flyingwhale
 * @date 2022/10/6
 */
@Data
public class PostMainInfoEntity {

    private Long id;

    private String uid;

    private String title;

    private String content;

    private String topicType;

    private String categoryType;

    private String userUid;

    private Integer likeCount;

    private Integer visitCount;

    private Integer isDelete;

    private Date createTime;

    private Date updateTime;

}
