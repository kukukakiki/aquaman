/*
MIT License

Copyright (c) 2019 wei wang

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.aquaman.security.common.example;

import com.aquaman.security.common.annotation.ExampleCode;
import com.aquaman.security.common.example.AutoCloseExecutorService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019-09-10
 * @since 2019-09-10
 */
@Slf4j
@ExampleCode("Semaphore示例代码")
public class SemaphoreExample {

    /**
     * 请求总数
     */
    public static int clientTotal = 5000;

    /**
     * 同时并行执行的线程数
     */
    public static int threadTotal = 200;

    /**
     * 这里使用int线程非安全，AtomicInteger将会是线程安全的使用方式
     */
    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        try(AutoCloseExecutorService executorService = new AutoCloseExecutorService()) {
            ExecutorService executorService1 = executorService.newCachedThreadPool();
            final Semaphore semaphore = new Semaphore(threadTotal);
            for(int i = 0; i < clientTotal; i++) {
                executorService1.execute(() -> {
                    try {
                        // 获取信号量
                        semaphore.acquire();
                        add();
                        // 释放信号量
                        semaphore.release();
                    } catch (Exception e) {
                        log.error("exception", e);
                    }
                });
            }
            log.info("count: {}", count);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void add() {
        count++;
    }
}
