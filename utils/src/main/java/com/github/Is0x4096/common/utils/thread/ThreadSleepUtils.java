package com.github.Is0x4096.common.utils.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 0x4096.peng@gmail.com
 * @project common-utils
 * @datetime 2020/6/26 17:31
 * @description
 * @readme
 */
public class ThreadSleepUtils {

    private static final Logger logger = LoggerFactory.getLogger(ThreadSleepUtils.class);

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
