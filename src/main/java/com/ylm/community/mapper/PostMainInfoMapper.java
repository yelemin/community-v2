package com.ylm.community.mapper;

import com.ylm.community.model.entity.PostMainInfoEntity;
import com.ylm.community.model.input.AddPostMainInput;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author flyingwhale
 * @date 2022/10/6
 */
public interface PostMainInfoMapper {


    /**
     * 批量查询
     * @param condition -
     * @param skip -
     * @param limit -
     * @return -
     */
    List<PostMainInfoEntity> searchByCondition(Condition condition, Integer skip, Integer limit);

    Long searchCountByCondition(Condition condition);

    void add(PostMainInfoEntity entity);

    @Data
    @Builder
    class Condition {

        private String categoryType;

        private String topicType;

    }

}
