package com.sea.controller;

import com.sea.enums.ResponseEnum;
import com.sea.response.BaseResponse;
import com.sea.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: jiaqi.cui
 * @date: 2023/1/12
 */
@RestController
@RequestMapping("init")
public class InitController {

    @Autowired
    private InitService initService;

    @RequestMapping("addPoetry")
    public BaseResponse addPoetry(@RequestBody String[] names) {
        for (String name : names) {
            initService.initPoetry(name);
        }
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

    @RequestMapping("addAuthor")
    public BaseResponse addAuthor(String fileName) {
        initService.initAuthor(fileName);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

}
