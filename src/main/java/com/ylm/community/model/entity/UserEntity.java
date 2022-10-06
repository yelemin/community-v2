package com.ylm.community.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author flyingwhale
 * @date 2022/10/4
 */
@Data
public class UserEntity {

    private Long id;

    private String uid;

    private String username;

    private String password;

    private String encryptedMobile;

    private String mobileUid;

    private String nickname;

    private String salt;

    private Integer isDelete;

    private Date createTime;

    private Date updateTime;

}
