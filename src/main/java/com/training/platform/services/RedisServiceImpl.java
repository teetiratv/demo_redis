package com.training.platform.services;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Map;
import java.util.Set;

@Service
public class RedisServiceImpl implements RedisService {

    private Jedis jedisCreateConnection() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        return jedis;
    }

    @Override
    public void set(String key, Object body) {
        Jedis jedis = this.jedisCreateConnection();

        SetParams setParams = new SetParams();
        setParams.ex(Long.parseLong("3600"));
        setParams.nx();

        jedis.set(key, body.toString(), setParams);
        jedis.close();
    }

    @Override
    public void update(String key, Object body) {
        Jedis jedis = this.jedisCreateConnection();

        SetParams setParams = new SetParams();
        setParams.ex(Long.parseLong("3600"));
        setParams.xx();

        jedis.set(key, body.toString(), setParams);
        jedis.close();
    }

    @Override
    public String get(String key) {
        Jedis jedis = this.jedisCreateConnection();
        String res = jedis.get(key);
        jedis.close();
        return res;
    }

    @Override
    public Long expire(Map<String, Object> body) {
        Jedis jedis = this.jedisCreateConnection();
        Long expire = jedis.expire(body.get("key").toString(), Long.parseLong(body.get("timeToExpire").toString()));
        jedis.close();
        return expire;
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = this.jedisCreateConnection();
        Long ttl = jedis.ttl(key);
        jedis.close();
        return ttl;
    }

    @Override
    public Set<String> keys(String patternKey) {
        Jedis jedis = this.jedisCreateConnection();
        Set<String> keys = jedis.keys(patternKey);
        jedis.close();
        return keys;
    }

    @Override
    public Boolean exists(String keys) {
        Jedis jedis = this.jedisCreateConnection();
        Boolean exists = jedis.exists(keys);
        jedis.close();
        return exists;
    }

    @Override
    public Long del(String keys) {
        Jedis jedis = this.jedisCreateConnection();
        Long del = jedis.del(keys);
        jedis.close();
        return del;
    }
}
