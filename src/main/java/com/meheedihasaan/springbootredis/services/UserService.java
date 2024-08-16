package com.meheedihasaan.springbootredis.services;

import com.meheedihasaan.springbootredis.enums.RedisPurpose;
import com.meheedihasaan.springbootredis.models.User;
import com.meheedihasaan.springbootredis.repositories.UserRepository;
import com.meheedihasaan.springbootredis.utils.RedisKeyGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RedisService redisService;

    public UUID create(User user) {
        user.setId(UUID.randomUUID());
        user = userRepository.save(user);
        return user.getId();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(UUID id) {
        return userRepository.findById(id);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    public String generateUserToken(UUID id) {
        User user = findById(id);
        if (user == null) {
            log.error("User not found with id: {}", id);
            return null;
        }

        String token = UUID.randomUUID().toString();
        String key = RedisKeyGenerator.generateKey(token, RedisPurpose.USER_TOKEN.name());
        redisService.addString(key, user.getEmail(), Duration.ofMinutes(1));
        return token;
    }

    public String getEmailByUserToken(String token) {
        String key = RedisKeyGenerator.generateKey(token, RedisPurpose.USER_TOKEN.name());
        String email = redisService.getString(key);
        log.info("Key: {}, Email: {}", key, email);
        return email;
    }
}
