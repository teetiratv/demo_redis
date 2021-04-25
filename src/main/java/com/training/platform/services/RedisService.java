package com.training.platform.services;

import java.util.Map;
import java.util.Set;

public interface RedisService {
    void set(String key, Object body);

    void update(String key, Object body);

    String get(String key);

    Long expire(Map<String, Object> body);

    Long ttl(String key);

    Set<String> keys(String patternKey);

    Boolean exists(String keys);

    Long del(String keys);
}
