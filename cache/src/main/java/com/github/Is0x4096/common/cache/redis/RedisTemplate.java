package com.github.Is0x4096.common.cache.redis;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author: 0x4096.peng@gmail.com
 * @date: 2021-03-05
 */
public interface RedisTemplate<K, V> {

    /*
     * ----------------------- 基本操作 -----------------------
     */

    /**
     * 删除key
     */
    Boolean delete(String key);

    /**
     * 批量删除key
     */
    Long deleteBatch(String... keys);

    /**
     * 批量删除key
     */
    Long deleteBatch(Collection<String> keys);

    /**
     * 序列化key
     */
    byte[] dump(String key);

    /**
     * 是否存在key
     */
    Boolean hasKey(String key);

    /**
     * 设置key过期时间
     *
     * @param key     key
     * @param timeout 过期时间
     * @param unit    时间单位
     * @return 是否成功
     */
    Boolean expire(String key, long timeout, TimeUnit unit);

    /**
     * 设置key在指定时间过期
     *
     * @param key  key
     * @param date 指定过期的时间
     * @return 是否成功
     */
    Boolean expireAt(String key, Date date);

    /**
     * 查找匹配的key
     *
     * @param pattern 正则表达式
     * @return 查找匹配的key
     */
    Set<String> keys(String pattern);

    /**
     * 将当前数据库的 key 移动到给定的数据库 db 当中
     *
     * @param key     key
     * @param dbIndex 数据库 db
     * @return 是否成功
     */
    Boolean move(String key, int dbIndex);

    /**
     * 移除 key 的过期时间，key 将持久保持
     */
    Boolean persist(String key);

    /**
     * 返回 key 的剩余的过期时间 默认毫秒
     *
     * @param key key
     * @return 过期时间 单位毫秒
     */
    Long getExpire(String key);

    /**
     * 返回 key 的剩余的过期时间
     *
     * @param key  key
     * @param unit 时间单位
     * @return 过期时间
     */
    Long getExpire(String key, TimeUnit unit);

    /**
     * 从当前数据库中随机返回一个 key
     */
    String randomKey();

    /**
     * 修改 key 的名称
     *
     * @param oldKey 旧key名
     * @param newKey 新key名
     */
    void rename(String oldKey, String newKey);

    /**
     * 仅当 newkey 不存在时，将 oldKey 改名为 newkey
     *
     * @param oldKey 旧key名
     * @param newKey 新key名
     * @return 修改 key 的名称
     */
    Boolean renameIfAbsent(String oldKey, String newKey);

    /**
     * 返回 key 所储存的值的类型
     */
    DataType type(String key);


    /*
     * ----------------------- string相关操作 -----------------------
     */

    /**
     * 设置指定 key 的值
     *
     * @param key   键
     * @param value 值
     */
    void set(String key, V value);

    /**
     * 获取指定 key 的值
     *
     * @param key 键
     * @return 获取指定 key 的值
     */
    V get(String key);


    /**
     * @param key   键
     * @param start value 字符串开始位置
     * @param end   value 字符串结束位置
     * @return 返回 key 中字符串值的子字符
     */
    String getRange(String key, long start, long end);

    /**
     * @param key   键
     * @param value 值
     * @return 将给定 key 的值设为 value ，并返回 key 的旧值(old value)
     */
    V getAndSet(String key, V value);

    /**
     * 对 key 所储存的字符串值，获取指定偏移量上的位(bit)
     *
     * @param key
     * @param offset
     */
    Boolean getBit(String key, long offset);


    /**
     * 批量获取
     *
     * @param keys
     * @return
     */
    List<V> multiGet(String... keys);

    /**
     * 批量获取
     *
     * @param keys
     * @return
     */
    List<V> multiGet(Collection<String> keys);


    /**
     * 设置ASCII码, 字符串'a'的ASCII码是97, 转为二进制是'01100001', 此方法是将二进制第offset位值变为value
     *
     * @param key
     * @param offset 位置
     * @param value  值,true为1, false为0
     * @return
     */
    Boolean setBit(String key, long offset, boolean value);

    /**
     * 将值 value 关联到 key ，并将 key 的过期时间设为 timeout
     *
     * @param key     键
     * @param value   值
     * @param timeout 过期时间
     * @param unit    时间单位
     */
    void setEx(String key, String value, long timeout, TimeUnit unit);

    /**
     * 只有在 key 不存在时设置 key 的值
     *
     * @param key
     * @param value
     * @return 之前已经存在返回false, 不存在返回true
     */
    Boolean setIfAbsent(String key, String value);

    /**
     * 只有在 key 不存在时设置 key 的值
     *
     * @param key     键
     * @param value   值
     * @param timeout 超时时间
     * @return 之前已经存在返回false, 不存在返回true
     */
    Boolean setIfAbsent(String key, String value, Duration timeout);


    /**
     * 只有在 key 不存在时设置 key 的值
     *
     * @param key     键
     * @param value   值
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return 之前已经存在返回false, 不存在返回true
     */
    Boolean setIfAbsent(String key, String value, long timeout, TimeUnit unit);


    /**
     * 用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始
     *
     * @param key
     * @param value
     * @param offset 从指定位置开始覆写
     */
    void setRange(String key, String value, long offset);

    /**
     * 获取字符串的长度
     *
     * @param key
     * @return
     */
    Long size(String key);

    /**
     * 批量添加 keys-values
     *
     * @param maps keys-values
     */
    void multiSet(Map<String, String> maps);

    /**
     * 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在
     *
     * @param maps keys-values
     * @return 之前已经存在返回false, 不存在返回true
     */
    Boolean multiSetIfAbsent(Map<String, String> maps);

    /**
     * 增加(自增长), 负数则为自减
     *
     * @param key       键
     * @param increment 增长的值
     * @return 自增后的数值
     */
    Long incrBy(String key, long increment);

    /**
     * 增加(自增长),
     *
     * @param key       键
     * @param increment 增长的值
     * @return 自增后的数值
     */
    Double incrByFloat(String key, double increment);

    /**
     * 将 value 追加到 key 的末尾
     *
     * @param key   键
     * @param value 值
     * @return
     */
    Integer append(String key, String value);


    /*
     * ----------------------- hash相关操作 -----------------------
     */

    /**
     * 获取存储在哈希表中指定字段的值
     *
     * @param key
     * @param field
     * @return
     */
    Object hGet(String key, String field);

    /**
     * 获取所有给定字段的值
     *
     * @param key
     * @return
     */
    Map<Object, Object> hGetAll(String key);


    /**
     * 获取所有给定字段的值
     *
     * @param key
     * @param fields
     * @return
     */
    List<Object> hMultiGet(String key, Collection<Object> fields);

    /**
     * 新增 hash
     *
     * @param key     键
     * @param hashKey hash field
     * @param value   值
     */
    void hPut(String key, String hashKey, String value);

    /**
     * 增加多个 hash
     *
     * @param key  键
     * @param maps hash
     */
    void hPutAll(String key, Map<String, String> maps);

    /**
     * 仅当hashKey不存在时才设置
     *
     * @param key
     * @param hashKey
     * @param value
     * @return
     */
    Boolean hPutIfAbsent(String key, String hashKey, String value);


    /**
     * 删除一个或多个哈希表字段
     *
     * @param key
     * @param fields
     * @return
     */
    Long hDelete(String key, Object... fields);

    /**
     * 查看哈希表 key 中，指定的字段是否存在
     *
     * @param key
     * @param field
     * @return
     */
    boolean hExists(String key, String field);


    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment
     *
     * @param key
     * @param field
     * @param increment
     * @return
     */
    Long hIncrBy(String key, Object field, long increment);

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment
     *
     * @param key
     * @param field
     * @param delta
     * @return
     */
    Double hIncrByFloat(String key, Object field, double delta);

    /**
     * 获取所有哈希表中的字段
     *
     * @param key
     * @return
     */
    Set<Object> hKeys(String key);

    /**
     * 获取哈希表中字段的数量
     *
     * @param key
     * @return
     */
    Long hSize(String key);

    /**
     * 获取哈希表中所有值
     *
     * @param key
     * @return
     */
    List<Object> hValues(String key);

    /**
     * 迭代哈希表中的键值对
     *
     * @param key
     * @param options
     * @return
     */
    Cursor<Map.Entry<Object, Object>> hScan(String key, ScanOptions options);


    /*
     * ----------------------- list相关操作 -----------------------
     */

    /**
     * 通过索引获取列表中的元素
     *
     * @param key
     * @param index
     * @return
     */
    V lIndex(String key, long index);

    /**
     * 获取列表指定范围内的元素
     *
     * @param key
     * @param start 开始位置, 0是开始位置
     * @param end   结束位置, -1返回所有
     * @return
     */
    List<V> lRange(String key, long start, long end);

    /**
     * 存储在list头部
     *
     * @param key
     * @param value
     * @return
     */
    Long lLeftPush(String key, String value);

    /**
     * @param key
     * @param value
     * @return
     */
    Long lLeftPushAll(String key, String... value);

    /**
     * @param key
     * @param value
     * @return
     */
    Long lLeftPushAll(String key, Collection<String> value);

    /**
     * 当list存在的时候才加入
     *
     * @param key
     * @param value
     * @return
     */
    Long lLeftPushIfPresent(String key, String value);

    /**
     * 如果pivot存在,再pivot前面添加
     *
     * @param key
     * @param pivot
     * @param value
     * @return
     */
    Long lLeftPush(String key, String pivot, String value);

    /**
     * @param key
     * @param value
     * @return
     */
    Long lRightPush(String key, String value);

    /**
     * @param key
     * @param value
     * @return
     */
    Long lRightPushAll(String key, String... value);

    /**
     * @param key
     * @param value
     * @return
     */
    Long lRightPushAll(String key, Collection<String> value);

    /**
     * 为已存在的列表添加值
     *
     * @param key
     * @param value
     * @return
     */
    Long lRightPushIfPresent(String key, String value);


    /**
     * 在pivot元素的右边添加值
     *
     * @param key
     * @param pivot
     * @param value
     * @return
     */
    Long lRightPush(String key, String pivot, String value);

    /**
     * 通过索引设置列表元素的值
     *
     * @param key
     * @param index 位置
     * @param value
     */
    void lSet(String key, long index, String value);

    /**
     * 移出并获取列表的第一个元素
     *
     * @param key
     * @return 删除的元素
     */
    V lLeftPop(String key);

    /**
     * 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     *
     * @param key
     * @param timeout 等待时间
     * @param unit    时间单位
     * @return
     */
    V lBLeftPop(String key, long timeout, TimeUnit unit);

    /**
     * 移除并获取列表最后一个元素
     *
     * @param key
     * @return 删除的元素
     */
    V lRightPop(String key);

    /**
     * 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     *
     * @param key
     * @param timeout 等待时间
     * @param unit    时间单位
     * @return
     */
    V lBRightPop(String key, long timeout, TimeUnit unit);

    /**
     * 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
     *
     * @param sourceKey
     * @param destinationKey
     * @return
     */
    V lRightPopAndLeftPush(String sourceKey, String destinationKey);

    /**
     * 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     *
     * @param sourceKey
     * @param destinationKey
     * @param timeout
     * @param unit
     * @return
     */
    V lBRightPopAndLeftPush(String sourceKey, String destinationKey, long timeout, TimeUnit unit);

    /**
     * 删除集合中值等于value得元素
     *
     * @param key
     * @param index index=0, 删除所有值等于value的元素; index>0, 从头部开始删除第一个值等于value的元素;
     *              index<0, 从尾部开始删除第一个值等于value的元素;
     * @param value
     * @return
     */
    Long lRemove(String key, long index, String value);


    /**
     * 裁剪list
     *
     * @param key
     * @param start
     * @param end
     */
    void lTrim(String key, long start, long end);

    /**
     * 获取列表长度
     *
     * @param key
     * @return
     */
    Long lLen(String key);


    /*
     * ----------------------- set相关操作 -----------------------
     */

    /**
     * set添加元素
     *
     * @param key
     * @param values
     * @return
     */
    Long sAdd(String key, String... values);


    /**
     * set移除元素
     *
     * @param key
     * @param values
     * @return
     */
    Long sRemove(String key, Object... values);

    /**
     * 移除并返回集合的一个随机元素
     *
     * @param key
     * @return
     */
    V sPop(String key);

    /**
     * 将元素value从一个集合移到另一个集合
     *
     * @param key
     * @param value
     * @param destKey
     * @return
     */
    Boolean sMove(String key, String value, String destKey);

    /**
     * 获取集合的大小
     *
     * @param key
     * @return
     */
    Long sSize(String key);

    /**
     * 判断集合是否包含value
     *
     * @param key
     * @param value
     * @return
     */
    Boolean sIsMember(String key, Object value);

    /**
     * 获取两个集合的交集
     *
     * @param key
     * @param otherKey
     * @return
     */
    Set<V> sIntersect(String key, String otherKey);

    /**
     * 获取key集合与多个集合的交集
     *
     * @param key
     * @param otherKeys
     * @return
     */
    Set<V> sIntersect(String key, Collection<String> otherKeys);

    /**
     * key集合与otherKey集合的交集存储到destKey集合中
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    Long sIntersectAndStore(String key, String otherKey, String destKey);

    /**
     * key集合与多个集合的交集存储到destKey集合中
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    Long sIntersectAndStore(String key, Collection<String> otherKeys, String destKey);

    /**
     * 获取两个集合的并集
     *
     * @param key
     * @param otherKeys
     * @return
     */
    Set<V> sUnion(String key, String otherKeys);

    /**
     * 获取key集合与多个集合的并集
     *
     * @param key
     * @param otherKeys
     * @return
     */
    Set<V> sUnion(String key, Collection<String> otherKeys);


    /**
     * key集合与otherKey集合的并集存储到destKey中
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    Long sUnionAndStore(String key, String otherKey, String destKey);

    /**
     * key集合与多个集合的并集存储到destKey中
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    Long sUnionAndStore(String key, Collection<String> otherKeys,
                        String destKey);

    /**
     * 获取两个集合的差集
     *
     * @param key
     * @param otherKey
     * @return
     */
    Set<V> sDifference(String key, String otherKey);

    /**
     * 获取key集合与多个集合的差集
     *
     * @param key
     * @param otherKeys
     * @return
     */
    Set<V> sDifference(String key, Collection<String> otherKeys);

    /**
     * key集合与otherKey集合的差集存储到destKey中
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    Long sDifference(String key, String otherKey, String destKey);


    /**
     * key集合与多个集合的差集存储到destKey中
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    Long sDifference(String key, Collection<String> otherKeys,
                     String destKey);

    /**
     * 获取集合所有元素
     *
     * @param key
     * @return
     */
    Set<V> setMembers(String key);

    /**
     * 随机获取集合中的一个元素
     *
     * @param key
     * @return
     */
    V sRandomMember(String key);

    /**
     * 随机获取集合中count个元素
     *
     * @param key
     * @param count
     * @return
     */
    List<V> sRandomMembers(String key, long count);

    /**
     * 随机获取集合中count个元素并且去除重复的
     *
     * @param key
     * @param count
     * @return
     */
    Set<V> sDistinctRandomMembers(String key, long count);


    /**
     * @param key
     * @param options
     * @return
     */
    Cursor<V> sScan(String key, ScanOptions options);


    /*
     * ----------------------- zSet相关操作 -----------------------
     */

    /**
     * 添加元素,有序集合是按照元素的score值由小到大排列
     *
     * @param key
     * @param value
     * @param score
     * @return
     */
    Boolean zAdd(String key, V value, double score);

    /**
     * @param key
     * @param values
     * @return
     */
    Long zAdd(String key, Set<ZSetOperations.TypedTuple<V>> values);

    /**
     * @param key
     * @param values
     * @return
     */
    Long zRemove(String key, Object... values);


    /**
     * 增加元素的score值，并返回增加后的值
     *
     * @param key
     * @param value
     * @param delta
     * @return
     */
    Double zIncrementScore(String key, String value, double delta);

    /**
     * 返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
     *
     * @param key
     * @param value
     * @return 0表示第一位
     */
    Long zRank(String key, Object value);

    /**
     * 返回元素在集合的排名,按元素的score值由大到小排列
     *
     * @param key
     * @param value
     * @return
     */
    Long zReverseRank(String key, Object value);

    /**
     * 获取集合的元素, 从小到大排序
     *
     * @param key
     * @param start 开始位置
     * @param end   结束位置, -1查询所有
     * @return
     */
    Set<V> zRange(String key, long start, long end);

    /**
     * 获取集合元素, 并且把score值也获取
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<ZSetOperations.TypedTuple<V>> zRangeWithScores(String key, long start, long end);

    /**
     * 根据Score值查询集合元素
     *
     * @param key
     * @param min 最小值
     * @param max 最大值
     * @return
     */
    Set<V> zRangeByScore(String key, double min, double max);

    /**
     * 根据Score值查询集合元素, 从小到大排序
     *
     * @param key
     * @param min 最小值
     * @param max 最大值
     * @return
     */
    Set<ZSetOperations.TypedTuple<V>> zRangeByScoreWithScores(String key, double min, double max);

    /**
     * @param key
     * @param min
     * @param max
     * @param start
     * @param end
     * @return
     */
    Set<ZSetOperations.TypedTuple<V>> zRangeByScoreWithScores(String key, double min, double max, long start, long end);

    /**
     * 获取集合的元素, 从大到小排序
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<V> zReverseRange(String key, long start, long end);

    /**
     * 获取集合的元素, 从大到小排序, 并返回score值
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<ZSetOperations.TypedTuple<V>> zReverseRangeWithScores(String key, long start, long end);

    /**
     * 根据Score值查询集合元素, 从大到小排序
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    Set<V> zReverseRangeByScore(String key, double min, double max);

    /**
     * 根据Score值查询集合元素, 从大到小排序
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    Set<ZSetOperations.TypedTuple<V>> zReverseRangeByScoreWithScores(String key, double min, double max);

    /**
     * @param key
     * @param min
     * @param max
     * @param start
     * @param end
     * @return
     */
    Set<V> zReverseRangeByScore(String key, double min, double max, long start, long end);

    /**
     * 根据score值获取集合元素数量
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    Long zCount(String key, double min, double max);

    /**
     * 获取集合大小
     *
     * @param key
     * @return
     */
    Long zSize(String key);

    /**
     * 获取集合大小
     *
     * @param key
     * @return
     */
    Long zZCard(String key);

    /**
     * 获取集合中value元素的score值
     *
     * @param key
     * @param value
     * @return
     */
    Double zScore(String key, Object value);

    /**
     * 移除指定索引位置的成员
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    Long zRemoveRange(String key, long start, long end);

    /**
     * 根据指定的score值的范围来移除成员
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    Long zRemoveRangeByScore(String key, double min, double max);

    /**
     * 获取key和otherKey的并集并存储在destKey中
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    Long zUnionAndStore(String key, String otherKey, String destKey);

    /**
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    Long zUnionAndStore(String key, Collection<String> otherKeys, String destKey);

    /**
     * 交集
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    Long zIntersectAndStore(String key, String otherKey, String destKey);


    /**
     * 交集
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    Long zIntersectAndStore(String key, Collection<String> otherKeys, String destKey);

    /**
     * @param key
     * @param options
     * @return
     */
    Cursor<ZSetOperations.TypedTuple<V>> zScan(String key, ScanOptions options);

}
