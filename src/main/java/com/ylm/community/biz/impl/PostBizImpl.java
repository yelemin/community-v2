package com.ylm.community.biz.impl;

import com.ylm.community.biz.PostBiz;
import com.ylm.community.model.entity.PostMainInfoEntity;
import com.ylm.community.model.input.AddPostMainInput;
import com.ylm.community.model.input.SearchPostMainInput;
import com.ylm.community.model.output.SearchPostMainOutput;
import com.ylm.community.service.PostService;
import com.ylm.community.utils.AssertUtil;
import com.ylm.community.utils.BeanUtils;
import com.ylm.community.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author flyingwhale
 * @date 2022/10/6
 */
@Service
@Slf4j
public class PostBizImpl implements PostBiz {

    @Resource
    private PostService postService;

    @Override
    public PageUtils.Page<SearchPostMainOutput> searchMainByCategory(SearchPostMainInput input) {
        AssertUtil.notNull(input);

        List<PostMainInfoEntity> entityList = postService.searchMain(input);
        return PageUtils.listPage(
                buildSearchPostMainOutput(entityList),
                postService.searchMainCount(input),
                input.getPageNo(),
                input.getPageSize()
        );
    }

    @Override
    public void publish(AddPostMainInput input) {
        AssertUtil.notNull(input);

        postService.add(input);
    }

    private List<SearchPostMainOutput> buildSearchPostMainOutput(List<PostMainInfoEntity> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return Collections.emptyList();
        }

        return BeanUtils.copyList(entityList, SearchPostMainOutput.class, (from, to) -> {
            to.setPostUid(from.getUid());
            to.setPostUserUid(from.getUserUid());
            //todo
            to.setPostUserNickName(StringUtils.EMPTY);
            to.setReplyCount(0);
        });
    }

}
