package com.github.x4096.common.utils.test.number;

import com.github.x4096.common.utils.number.SnowFlakeUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

/**
 * @author 0x4096.peng@gmail.com
 * @project common-utils
 * @datetime 2020/6/25 23:22
 * @description
 * @readme
 */
public class SnowFlakeUtilsTest {

    @Test
    public void test() {
        RandomUtils.nextInt(1, 10);
        for (int i = 0; i < 10; i++) {

            System.err.println(SnowFlakeUtils.generate());
        }
    }

}
