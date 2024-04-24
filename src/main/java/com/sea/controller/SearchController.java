package com.sea.controller;

import com.sea.enums.ResponseEnum;
import com.sea.response.BaseResponse;
import com.sea.rquest.SearchReq;
import com.sea.service.MeiliSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private MeiliSearchService meiliSearchService;

    @RequestMapping("simple")
    public BaseResponse simple(String keyword) {
        return new BaseResponse(ResponseEnum.SUCCESS, meiliSearchService.keywordQuery(keyword));
    }

    @RequestMapping("multiple")
    public BaseResponse multiple(@RequestBody SearchReq req) {
        return new BaseResponse(ResponseEnum.SUCCESS, meiliSearchService.multipleQuery(req));
    }

    @RequestMapping("sortConfig")
    public BaseResponse sortConfig(@RequestBody String[] sorts) {
        meiliSearchService.updateSorts(sorts);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

    @RequestMapping("filterConfig")
    public BaseResponse filterConfig(@RequestBody String[] filters) {
        meiliSearchService.updateFilters(filters);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }
}
