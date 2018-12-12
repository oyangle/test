package com.yangle.service.biz.redis.impl;

import com.alibaba.fastjson.JSONObject;
import com.yangle.service.biz.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * programname: product_factory
 * <p>
 * title: redis服务实习类
 *
 * @author: yishao
 * <p>
 * created: 2018-12-06 17:51
 **/
@Service
public class RedisServiceImpl implements RedisService {

    private final static Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

    public final static String DATA_REDIS_KEY = "test_";

    /**
     * redis过期时间,以秒为单位
     */
    public final static int EXRP_HOUR = 60 * 60;            //一小时
    public final static int EXRP_HALF_DAY = 60 * 60 * 12;   //半天
    public final static int EXRP_DAY = 60 * 60 * 24;        //一天
    public final static int EXRP_MONTH = 60 * 60 * 24 * 30; //一个月

    @Resource
    private JedisPool jedisPool;

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
    public <V> void setVExpire(String key, V value,int seconds,int index) {
        String json = JSONObject.toJSONString(value);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(index);
            jedis.set(DATA_REDIS_KEY +key, json);
            jedis.expire(DATA_REDIS_KEY +key, seconds);
        } catch (Exception e) {
            logger.error("setV初始化jedis异常：" + e);
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            closeJedis(jedis);
        }

    }
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
    public  <V> void setV(String key, V value,int index) {
        String json = JSONObject.toJSONString(value);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(index);
            jedis.set(DATA_REDIS_KEY +key, json);
        } catch (Exception e) {
            logger.error("setV初始化jedis异常：" + e);
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            closeJedis(jedis);
        }

    }

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
    @SuppressWarnings("unchecked")
    public  <V> V getV(String key,int index) {
        String value = "";
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(index);
            value = jedis.get(DATA_REDIS_KEY +key);
        } catch (Exception e) {
            logger.error("getV初始化jedis异常：" + e);
            if (jedis != null) {
                //jedisPool.returnBrokenResource(jedis);
                jedis.close();
            }
        } finally {
            closeJedis(jedis);
        }
        return (V)JSONObject.parse(value);
    }
    /**
     *
     * getVString(返回json字符串)
     * @Title: getVString
     * @param @param key
     * @param @param index
     * @param @return
     * @return String
     * @throws
     */
    public  String getVStr(String key,int index) {
        String value = "";
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(index);
            value = jedis.get(DATA_REDIS_KEY +key);
        } catch (Exception e) {
            logger.error("getVString初始化jedis异常：" + e);
            if (jedis != null) {
                //jedisPool.returnBrokenResource(jedis);
                jedis.close();
            }
        } finally {
            closeJedis(jedis);
        }
        return value;
    }

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
    public  <V> void push(String key, V value) {
        String json = JSONObject.toJSONString(value);
        Jedis jedis = null;
        try {
            logger.info("存入 数据到队列中");
            jedis = jedisPool.getResource();
            jedis.select(15);
            jedis.lpush(key, json);
        } catch (Exception e) {
            logger.error("Push初始化jedis异常：" + e);
            if (jedis != null) {
                //jedisPool.returnBrokenResource(jedis);
                jedis.close();
            }
        } finally {
            closeJedis(jedis);
        }
    }
    /**
     *
     * Push(存入 数据到队列中)
     *
     * @Title: PushV
     * @param  key
     * @param value
     * @param dBNum
     * @return void
     * @throws
     */
    public  <V> void pushV(String key, V value,int dBNum) {
        String json = JSONObject.toJSONString(value);
        Jedis jedis = null;
        try {
            logger.info("存入 数据到队列中");
            jedis = jedisPool.getResource();
            jedis.select(dBNum);
            jedis.lpush(key, json);
        } catch (Exception e) {
            logger.error("Push初始化jedis异常：" + e);
            if (jedis != null) {
                //jedisPool.returnBrokenResource(jedis);
                jedis.close();
            }
        } finally {
            closeJedis(jedis);
        }
    }

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
    public  <V> void pushEmail(String key, V value) {

        String json = JSONObject.toJSONString(value);
        Jedis jedis = null;
        try {
            logger.info("存入 数据到队列中");
            jedis = jedisPool.getResource();
            jedis.select(15);
            jedis.lpush(key, json);
        } catch (Exception e) {
            logger.error("Push初始化jedis异常：" + e);
            if (jedis != null) {
                //jedisPool.returnBrokenResource(jedis);
                jedis.close();
            }
        } finally {
            closeJedis(jedis);
        }
    }

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
    @SuppressWarnings("unchecked")
    public  <V> V pop(String key) {
        String value = "";
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(15);
            value = jedis.rpop(DATA_REDIS_KEY +key);
        } catch (Exception e) {
            logger.error("Pop初始化jedis异常：" + e);
            if (jedis != null) {
                //jedisPool.returnBrokenResource(jedis);
                jedis.close();
            }
        } finally {
            closeJedis(jedis);
        }
        return (V) value;
    }


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
    public  void expireKey(String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(3);
            jedis.expire(DATA_REDIS_KEY + key, seconds);
        } catch (Exception e) {
            logger.error("Pop初始化jedis异常：" + e);
            if (jedis != null) {
                jedis.close();
            }
        } finally {
            closeJedis(jedis);
        }

    }

    /**
     *
     * closeJedis(释放redis资源)
     *
     * @Title: closeJedis
     * @param @param jedis
     * @return void
     * @throws
     */
    public void closeJedis(Jedis jedis) {
        try {
            if (jedis != null) {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error("释放资源异常：" + e);
        }
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
