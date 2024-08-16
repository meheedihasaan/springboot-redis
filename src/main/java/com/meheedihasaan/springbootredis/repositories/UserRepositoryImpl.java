package com.meheedihasaan.springbootredis.repositories;

import com.meheedihasaan.springbootredis.models.User;
import com.meheedihasaan.springbootredis.services.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final String KEY = "USER";

    private final RedisService redisService;

    @Override
    public User save(User user) {
        redisService.addObject(KEY, user.getId(), user);
        return user;
    }

    @Override
    public List<User> findAll() {
        Map<Object, Object> users = redisService.getAllObjects(KEY);
        return users.values().stream().map((user)-> (User) user).toList();
    }

    @Override
    public User findById(UUID id) {
        return (User) redisService.getObject(KEY, id);
    }

    @Override
    public void deleteById(UUID id) {
        redisService.deleteObject(KEY, id);
    }
}
