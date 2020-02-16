package com.imooc.miaosha.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisService {
    @Autowired
    JedisPool jedisPool;
    @Autowired
    RedisConfig redisConfig;

    /**
     * 获取单个对象
     * @param keyPrefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix keyPrefix,String key, Class<T> clazz){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = keyPrefix.getPrefix()+key;
            String s = jedis.get(realKey);
            return stringToBean(s,clazz);

        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 设置对象
     * @param keyPrefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix keyPrefix,String key, T value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if(str==null||str.length()<=0){
                return false;
            }
            //生成真正的key
            String realKey = keyPrefix.getPrefix()+key;
            int expireSeconds = keyPrefix.expireSeconds();
            if (expireSeconds<=0){

                jedis.set(realKey, str);
            }else {
                jedis.setex(realKey,expireSeconds,str);
            }
            return true;

        }finally {
            returnToPool(jedis);
        }
    }
    public <T> boolean exists(KeyPrefix keyPrefix,String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = keyPrefix.getPrefix()+key;
            return jedis.exists(realKey);

        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 增加值
     * @param keyPrefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long incr(KeyPrefix keyPrefix,String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = keyPrefix.getPrefix()+key;
            return jedis.incr(realKey);

        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 减少值
     * @param keyPrefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long decr(KeyPrefix keyPrefix,String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = keyPrefix.getPrefix()+key;
            return jedis.decr(realKey);

        }finally {
            returnToPool(jedis);
        }
    }

    private <T> T stringToBean(String s ,Class<T> clazz) {
        if (StringUtils.isEmpty(s))
            return null;
        if (clazz==int.class||clazz==Integer.class)
            return (T) Integer.valueOf(s);
        if (clazz==String.class)
            return (T) s;
        return JSON.parseObject(s,clazz);

    }

    private <T> String beanToString(T value) {
        if (null == value)
            return null;
        Class<?> clazz = value.getClass();
        if (clazz==int.class||clazz==Integer.class)
            return ""+value;
        else if (clazz==String.class)
            return (String) value;
        else if (clazz==long.class||clazz==Long.class)
            return ""+value;
        else
            return JSON.toJSONString(value);
    }

    private void returnToPool(Jedis jedis) {
        if (jedis!=null)
            jedisPool.returnResource(jedis);
    }


}
