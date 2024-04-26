package com.sea.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: jiaqi.cui
 * @date: 2024/4/26
 */
@Slf4j
@Service
public class AsyncService {

    /**
     * @Async注释方法的返回值只能为void或Future
     * @Async不填线程池名称就用spring默认的
     */
    @Async("taskExecutor")
    public void sendMsg1() throws InterruptedException {
        log.info("AsyncService sendMsg1 start");
        Thread.sleep(5000);
        log.info("AsyncService sendMsg1 end");
    }

    @Async("taskExecutor")
    public void sendMsg2() throws InterruptedException {
        log.info("AsyncService sendMsg2 start");
        Thread.sleep(3000);
        log.info("AsyncService sendMsg2 end");
    }
}
