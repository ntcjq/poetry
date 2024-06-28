package com.sea.controller;

import com.github.houbb.heaven.util.util.DateUtil;
import com.sea.bean.Author;
import com.sea.dao.AuthorRepository;
import com.sea.enums.ResponseEnum;
import com.sea.response.BaseResponse;
import com.sea.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;

/**
 * @description:
 * @author: jiaqi.cui
 * @date: 2024/4/25
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private AsyncService asyncService;
    @Autowired
    private AuthorRepository authorRepository;

    @RequestMapping("retry")
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public String retry() {
        log.info("------------retry--------" + DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(1 / 0);
        return "Retryable";
    }

    /**
     * @Retryable无法成功就会走@Recover兜底 注意：
     * 1.@Recover返回值要和@Retryable的一样
     * 2.@Recover抛出的异常要和@Retryable接收的异常一样
     */
    @Recover
    public String recover(Exception e) throws Exception {
        log.info("------------recover--------" + DateUtil.getDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return "Recover";
    }


    /**
     * Async异步注解测试
     *
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("async")
    public BaseResponse async() throws InterruptedException {
        log.info("TestController async start");
        log.info("TestController async normal business");
        asyncService.sendMsg1();
        asyncService.sendMsg2();
        log.info("TestController async end");
        return new BaseResponse(ResponseEnum.SUCCESS);
    }


    @RequestMapping("insert")
    public BaseResponse insert() {
        String profile = """
                ABC
                DEF
                GHI
                JKL
                """;
        Author author = new Author();
        author.setName("Sea");
        author.setProfile(profile);
        author.setTags("SONG");
        authorRepository.save(author);
        author = null;
        author.getId();
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

    public static void main(String[] args) {

        Instant now = Instant.now();
        System.out.println(now);

        String profile = " ABC ";
        profile = profile.strip();
        System.out.println(profile);
        profile = profile.repeat(2);
        System.out.println(profile);
    }
}
