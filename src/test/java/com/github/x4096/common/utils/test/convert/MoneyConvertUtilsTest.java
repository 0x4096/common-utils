package com.github.x4096.common.utils.test.convert;

import com.github.x4096.common.utils.convert.MoneyConvertUtils;
import org.junit.jupiter.api.Test;

/**
 * @author 0x4096.peng@gmail.com
 * @date 2020/7/19
 */
public class MoneyConvertUtilsTest {

    @Test
    public void test() {
        int weixin = MoneyConvertUtils.changeY2F("10238338.84");
        int alipay = MoneyConvertUtils.changeY2F("1702857.21");

        System.err.println(weixin + alipay);

    }

}
