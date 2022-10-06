package com.ylm.community.biz;

import com.ylm.community.model.input.AddPostMainInput;
import com.ylm.community.model.input.SearchPostMainInput;
import com.ylm.community.model.output.SearchPostMainOutput;
import com.ylm.community.utils.PageUtils;

/**
 * @author flyingwhale
 * @date 2022/10/6
 */
public interface PostBiz {

    PageUtils.Page<SearchPostMainOutput> searchMainByCategory(SearchPostMainInput input);

    void publish(AddPostMainInput input);

}
