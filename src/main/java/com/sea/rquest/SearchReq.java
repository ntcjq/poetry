package com.sea.rquest;

import lombok.Data;

/**
 * @description:
 * @author: jiaqi.cui
 * @date: 2024/4/23
 */
@Data
public class SearchReq {
    private String keyword;
    private String[] filter;
    private String[] sort;
    private Integer page;//页码
    private Integer size;//每页条数
}
