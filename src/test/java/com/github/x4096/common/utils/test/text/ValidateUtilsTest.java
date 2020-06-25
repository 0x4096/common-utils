package com.github.x4096.common.utils.test.text;

import com.github.x4096.common.utils.text.ValidateUtils;
import org.junit.jupiter.api.Test;

/**
 * @author 0x4096.peng@gmail.com
 * @project common-utils
 * @datetime 2020/6/13 00:11
 * @description
 * @readme
 */
public class ValidateUtilsTest {

    @Test
    public void isEmail() {
        System.err.println(ValidateUtils.isEmail(""));
    }

    @Test
    public void isJSON() {
        System.err.println(ValidateUtils.isJSON("666"));
    }

}
