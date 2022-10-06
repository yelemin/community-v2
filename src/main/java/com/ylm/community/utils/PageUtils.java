package com.ylm.community.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;
import com.ylm.community.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author flyingwhale
 * @date 2022/10/6
 */

public class PageUtils {

    /**
     * 设置默认每页数据量以及最大值
     */
    private static final int MAX_PAGE_SIZE = 200;

    public static <T> Page<T> emptyPage(Integer pageSize) {
        return Page.<T>builder()
                .data(Lists.newArrayList())
                .total(0L)
                .pageNo(1)
                .pageSize(pageSize)
                .build();
    }

    public static <T> Page<T> listPage(
            List<T> data,
            Long total,
            Integer pageNo,
            Integer pageSize) {
        AssertUtil.isNatural(total, ErrorCode.SYSTEM_PARAM_ERROR, "总数必须大于等于0");
        validPage(pageNo);
        validPage(pageSize);
        AssertUtil.isPositive(pageSize, ErrorCode.SYSTEM_PARAM_ERROR, "页高必须大于等于1");
        return Page.<T>builder()
                .data(data)
                .total(total)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .build();
    }

    public static void validPage(Integer page) {
        AssertUtil.isPositive(page, ErrorCode.SYSTEM_PARAM_ERROR, "页码必须大于等于1");
    }

    public static void validPageSize(Integer pageSize) {
        AssertUtil.isPositive(pageSize, ErrorCode.SYSTEM_PARAM_ERROR, "页高必须大于等于1");
        AssertUtil.isTrue(pageSize <= MAX_PAGE_SIZE, ErrorCode.SYSTEM_PARAM_ERROR, "页高不得大于" + MAX_PAGE_SIZE);
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Page<T> {

        private List<T> data;
        private Long total;
        private Integer pageNo;
        private Integer pageSize;
        private Integer nextPage;
        private Boolean hasNext;

        public static <W> Page<W> copyFrom(Page<W> pageInfo) {
            return Page.<W>builder().total(pageInfo.total).pageSize(pageInfo.pageSize).pageNo(pageInfo.pageNo)
                    .nextPage(pageInfo.nextPage).hasNext(pageInfo.hasNext).build();
        }

    }

}
