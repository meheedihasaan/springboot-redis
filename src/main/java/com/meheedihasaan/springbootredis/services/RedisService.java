package com.meheedihasaan.springbootredis.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void addString(String key, Object value, Duration duration) {
        redisTemplate.opsForValue().set(key, value, duration);
    }

    public String getString(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void addObject(String key, Object hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public Map<Object, Object> getAllObjects(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public Object getObject(String key, Object hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    public void deleteObject(String key, Object hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }
}
