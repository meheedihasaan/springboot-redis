package com.meheedihasaan.springbootredis.repositories;

import com.meheedihasaan.springbootredis.models.User;

import java.util.List;
import java.util.UUID;
;
public interface UserRepository {

    User save(User user);

    List<User> findAll();

    User findById(UUID id);

    void deleteById(UUID id);
}