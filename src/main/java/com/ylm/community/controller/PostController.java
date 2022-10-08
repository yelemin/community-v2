package com.ylm.community.controller;

import com.ylm.community.biz.PostBiz;
import com.ylm.community.model.base.Response;
import com.ylm.community.model.input.AddPostMainInput;
import com.ylm.community.model.input.GetPostDetailInput;
import com.ylm.community.model.input.SearchPostMainInput;
import com.ylm.community.model.output.GetPostDetailOutput;
import com.ylm.community.model.output.SearchPostMainOutput;
import com.ylm.community.utils.PageUtils;
import com.ylm.community.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author flyingwhale
 * @date 2022/10/6
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/community/post")
@CrossOrigin
public class PostController {

    @Resource
    private PostBiz postBiz;

    @PostMapping(path = "/search-main-by-category", name = "根据类别查询贴子列表")
    public Response<PageUtils.Page<SearchPostMainOutput>> searchMainByCategory(@RequestBody SearchPostMainInput input) {
        return ResponseUtil.makeSuccess(postBiz.searchMainByCategory(input));
    }

    @PostMapping(path = "/publish", name = "发布贴子")
    public Response<Void> publish(@RequestBody AddPostMainInput input) {
        postBiz.publish(input);
        return ResponseUtil.makeSuccess(null);
    }

    @PostMapping(path = "/get-detail", name = "发布贴子")
    public Response<GetPostDetailOutput> getDetail(@RequestBody GetPostDetailInput input) {
        // todo 获取登录用户userId


        return ResponseUtil.makeSuccess(postBiz.getDetail(input));
    }

}
