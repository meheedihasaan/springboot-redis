package com.meheedihasaan.springbootredis.services;

import com.meheedihasaan.springbootredis.models.User;
import com.meheedihasaan.springbootredis.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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
}
