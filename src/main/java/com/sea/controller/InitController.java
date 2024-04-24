package com.sea.controller;

import com.sea.enums.ResponseEnum;
import com.sea.response.BaseResponse;
import com.sea.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public BaseResponse addPoetry() {
        String fileName = "";
        for (int i = 461; i <= 900; i++) {
            if (i < 10) {
                fileName = "00" + i + ".json";
            } else if (i < 100) {
                fileName = "0" + i + ".json";
            } else {
                fileName = i + ".json";
            }
            System.out.println("------------------:" + i);
            initService.initPoetry(fileName);
        }
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

    @RequestMapping("addAuthor")
    public BaseResponse addAuthor(String fileName) {
        initService.initAuthor(fileName);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

}
