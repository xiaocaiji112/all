package com.shop.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import javax.annotation.Resource;
import java.util.Set;

@Component
@Slf4j
public class RedisTemplate {
    //或者autoware
    private final JedisPool jedisPool = new JedisPool();


    private final ObjectMapper objectMapper  = new ObjectMapper();


    public String set(String  key,String value, Long expire){
        Jedis jedis = jedisPool.getResource();
        String returnValue = null;
        try {
            returnValue = jedis.setex(key, expire, value);
        }catch (JedisException jedisException){
            log.error("redis execution error",jedisException);
            jedisPool.returnBrokenResource(jedis);
        }finally {
            jedisPool.returnResource(jedis);
        }
        return returnValue;
    }

    public String get(String  key){
        Jedis jedis = jedisPool.getResource();
        String returnValue = null;
        try {
            returnValue = jedis.get(key);
        }catch (JedisException jedisException){
            log.error("redis execution error",jedisException);
            jedisPool.returnBrokenResource(jedis);
        }finally {
            jedisPool.returnResource(jedis);
        }
        return returnValue;
    }

    public String setObject(String  key,Object value, Long expire){
        Jedis jedis = jedisPool.getResource();
        String returnValue = null;
        try {
            //先序列化成json
            String jsonValue = objectMapper.writeValueAsString(value);
            if(expire <=0){
                returnValue = jedis.set(key, jsonValue);
            }else {
                returnValue = jedis.setex(key, expire, jsonValue);
            }
        }catch (JedisException jedisException){
            log.error("redis execution error",jedisException);
            jedisPool.returnBrokenResource(jedis);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            jedisPool.returnResource(jedis);
        }
        return returnValue;
    }

    public <T> T getObject(String  key,Class<T> clazz){
        Jedis jedis = jedisPool.getResource();
        String returnValue = null;
        T object = null;
        try {
            //从redis中获取
            returnValue = jedis.get(key);
            object = objectMapper.readValue(returnValue, clazz);
        }catch (JedisException jedisException){
            log.error("redis execution error",jedisException);
            jedisPool.returnBrokenResource(jedis);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            jedisPool.returnResource(jedis);
        }
        return object;
    }


    public String remove(String  key){
        Jedis jedis = jedisPool.getResource();
        String returnValue = null;
        try {
            jedis.del(key);
        }catch (JedisException jedisException){
            log.error("redis execution error",jedisException);
            jedisPool.returnBrokenResource(jedis);
        }finally {
            jedisPool.returnResource(jedis);
        }
        return returnValue;
    }

    public long addTime(String  key ,Long expire){
        Jedis jedis = jedisPool.getResource();
        long expire1 = 0;
        try {
            expire1 = jedis.expire(key, expire);
        }catch (JedisException jedisException){
            log.error("redis execution error",jedisException);
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return expire1;
    }

    public  Set<String> key(String  key ){
        Jedis jedis = jedisPool.getResource();
        Set<String> keys = null;
        try {
            keys = jedis.keys(key);
        }catch (JedisException jedisException){
            log.error("redis execution error",jedisException);
            jedisPool.returnBrokenResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return keys;
    }

}