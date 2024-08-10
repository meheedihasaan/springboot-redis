package com.meheedihasaan.springbootredis.repositories;

import com.meheedihasaan.springbootredis.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final String KEY = "USER";

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public User save(User user) {
        redisTemplate.opsForHash().put(KEY, user.getId(), user);
        return user;
    }

    @Override
    public List<User> findAll() {
        Map<Object, Object> users = redisTemplate.opsForHash().entries(KEY);
        return users.values().stream().map((user)-> (User) user).toList();
    }

    @Override
    public User findById(UUID id) {
        return (User) redisTemplate.opsForHash().get(KEY, id);
    }

    @Override
    public void deleteById(UUID id) {
        redisTemplate.opsForHash().delete(KEY, id);
    }
}
