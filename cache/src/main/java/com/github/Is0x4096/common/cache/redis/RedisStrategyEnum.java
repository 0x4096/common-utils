package com.github.Is0x4096.common.cache.redis;

/**
 * Redis 搭建模式
 *
 * @author 0x4096.peng@gmail.com
 * @date 2021/3/5
 */
public enum RedisStrategyEnum {

    /**
     * 单节点模式
     */
    SINGLE,

    /**
     * 单节点 连接池
     */
    SINGLE_POOL,

    /**
     * 分片模式
     */
    SHARDED,

    /**
     * 哨兵模式集群
     */
    SENTINEL,

    /**
     * 集群
     */
    CLUSTER

}
