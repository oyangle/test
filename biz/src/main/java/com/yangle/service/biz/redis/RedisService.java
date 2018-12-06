package com.yangle.service.biz.redis;

import redis.clients.jedis.Jedis;

/**
 * @program: product_factory
 * @description: redis服务类
 * @author: yishao
 * @create: 2018-12-06 17:50
 **/
public interface RedisService {

    /**
     *
     * setVExpire(设置key值，同时设置失效时间 秒)
     * @Title: setVExpire
     * @param @param key
     * @param @param value
     * @param @param seconds
     * @param index 具体数据库 默认使用0号库
     * @return void
     * @throws
     */
    <V> void setVExpire(String key, V value,int seconds,int index);

    /**
     *
     * (存入redis数据)
     * @Title: setV
     * @param @param key
     * @param @param value
     * @param index 具体数据库
     * @return void
     * @throws
     */
    <V> void setV(String key, V value,int index);

    /**
     *
     * getV(获取redis数据信息)
     * @Title: getV
     * @param @param key
     * @param index 具体数据库 0:常用数据存储      3：session数据存储
     * @param @return
     * @return V
     * @throws
     */
    <V> V getV(String key,int index);

    /**
     *
     * Push(存入 数据到队列中)
     *
     * @Title: Push
     * @param @param key
     * @param @param value
     * @return void
     * @throws
     */
    <V> void pushEmail(String key, V value);

    /**
     *
     * Pop(从队列中取值)
     *
     * @Title: Pop
     * @param @param key
     * @param @return
     * @return V
     * @throws
     */
    <V> V pop(String key);

    /**
     *
     * expireKey(限时存入redis服务器)
     *
     * @Title: expireKey
     * @param @param key
     * @param @param seconds
     * @return void
     * @throws
     */
    void expireKey(String key, int seconds);

    /**
     *
     * closeJedis(释放redis资源)
     *
     * @Title: closeJedis
     * @param @param jedis
     * @return void
     * @throws
     */
    void closeJedis(Jedis jedis);
}
