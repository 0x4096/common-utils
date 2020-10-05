package com.github.x4096.common.utils.test.env;

import org.junit.jupiter.api.Test;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author 0x4096.peng@gmail.com
 * @date 2020/10/5
 */
public class RuntimeUtilsTest {

    @Test
    public void test() throws UnknownHostException {

        String name = ManagementFactory.getRuntimeMXBean().getName();

        System.err.println(name);

        name = InetAddress.getLocalHost().getHostName();

        System.err.println(name);

    }

}
