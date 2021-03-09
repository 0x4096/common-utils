package com.github.Is0x4096.common.utils.number;

import com.github.Is0x4096.common.utils.network.IPUtils;

/**
 * @author 0x4096.peng@gmail.com
 * @project common-utils
 * @datetime 2020/6/25 23:14
 */
public class SnowFlakeUtils {

    private SnowFlakeUtils() {
    }

    private static SnowFlake snowFlake;

    /**
     * 18位 exp: 473671777722314758
     */
    public static long generate() {
        if (null == snowFlake) {
            synchronized (SnowFlakeUtils.class) {
                if (null == snowFlake) {
                    snowFlake = genSnowFlake();
                }
            }
        }
        return snowFlake.nextId();
    }

    public static SnowFlake getSnowFlake() {
        return snowFlake;
    }

    public static void setSnowFlake(SnowFlake snowFlake) {
        SnowFlakeUtils.snowFlake = snowFlake;
    }

    private static SnowFlake genSnowFlake() {
        String localIpAddr = IPUtils.getLocalIpAddr();
        if (null == localIpAddr) {
            localIpAddr = "127.0.0.1";
        }

        int hashCode = localIpAddr.hashCode() < 0 ? ~localIpAddr.hashCode() + 1 : localIpAddr.hashCode();
        hashCode = hashCode % 31;
        return new SnowFlake(hashCode, hashCode + 1);
    }

}
