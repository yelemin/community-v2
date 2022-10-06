package com.ylm.community.service.impl;

import com.ylm.community.mapper.PostMainInfoMapper;
import com.ylm.community.model.entity.PostMainInfoEntity;
import com.ylm.community.model.input.AddPostMainInput;
import com.ylm.community.model.input.SearchPostMainInput;
import com.ylm.community.service.PostService;
import com.ylm.community.utils.AssertUtil;
import com.ylm.community.utils.RadixParserUtils;
import com.ylm.community.utils.SnowflakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author flyingwhale
 * @date 2022/10/6
 */
@Service
@Slf4j
public class PostServiceImpl implements PostService {

    @Resource
    private PostMainInfoMapper postMainInfoMapper;
    @Resource
    private SnowflakeUtils snowflakeUtils;

    @Override
    public List<PostMainInfoEntity> searchMain(SearchPostMainInput input) {
        AssertUtil.notNull(input);

        return  postMainInfoMapper.searchByCondition(
                PostMainInfoMapper.Condition.builder()
                        .categoryType(input.getCategoryType())
                        .topicType(input.getTopicType()).build(),
                (input.getPageNo() - 1) * input.getPageSize(),
                input.getPageSize()
                );
    }

    @Override
    public Long searchMainCount(SearchPostMainInput input) {
        AssertUtil.notNull(input);

        return  postMainInfoMapper.searchCountByCondition(
                PostMainInfoMapper.Condition.builder()
                        .categoryType(input.getCategoryType())
                        .topicType(input.getTopicType()).build()
        );
    }

    @Override
    public void add(AddPostMainInput input) {
        AssertUtil.notNull(input);

        String salt = generateUid();
        PostMainInfoEntity entity = new PostMainInfoEntity();
        entity.setUid(salt);
        entity.setTitle(input.getTitle());
        entity.setContent(input.getContent());
        entity.setTopicType(input.getTopicType());
        entity.setCategoryType(input.getCategoryType());
        entity.setUserUid(input.getUserUid());
        entity.setLikeCount(0);
        entity.setVisitCount(0);
        postMainInfoMapper.add(entity);
    }

    private String generateUid(){
        return RadixParserUtils.encode(snowflakeUtils.nextId(), 12);
    }

}
