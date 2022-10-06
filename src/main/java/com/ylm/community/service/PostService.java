package com.ylm.community.service;

import com.ylm.community.model.entity.PostMainInfoEntity;
import com.ylm.community.model.input.AddPostMainInput;
import com.ylm.community.model.input.SearchPostMainInput;

import java.util.List;

/**
 * @author flyingwhale
 * @date 2022/10/6
 */
public interface PostService {

    List<PostMainInfoEntity> searchMain(SearchPostMainInput input);

    Long searchMainCount(SearchPostMainInput input);

    void add(AddPostMainInput input);

}
