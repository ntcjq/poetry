package com.sea.controller;

import com.sea.enums.ResponseEnum;
import com.sea.response.BaseResponse;
import com.sea.service.MeiliSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("document")
public class DocumentController {

    @Autowired
    private MeiliSearchService meiliSearchService;

    @RequestMapping("init")
    public BaseResponse init() {
        meiliSearchService.initDocumnets();
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

    @RequestMapping("deleteAll")
    public BaseResponse deleteAll() {
        meiliSearchService.deleteAllDocumnets();
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

}
