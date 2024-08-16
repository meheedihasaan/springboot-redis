package com.meheedihasaan.springbootredis.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RedisKeyGenerator {

    public static String generateKey(String token, String purpose) {
        return "SPRINGBOOT_REDIS::TOKEN:" + token + "::PURPOSE:" + purpose;
    }
}
