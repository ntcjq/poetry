package com.sea.controller;

import com.github.houbb.heaven.util.util.DateUtil;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @description:
 * @author: jiaqi.cui
 * @date: 2024/4/25
 */
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping("retry")
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public String retry() {
        System.out.println("------------retry--------" + DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(1 / 0);
        return "Retryable";
    }

    /**
     * @Retryable无法成功就会走@Recover兜底（注意：@Recover返回值要和@Retryable的一样）
     */
    @Recover
    public String cover() {
        return "Recover";
    }
}
