package com.zl.bifrost.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.timeout:30000}")
    private int timeout;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.maxIdle:10}")
    private int maxIdle;

    @Value("${redis.maxWaitMillis: 15000}")
    private int maxWaitMillis;

    @Value("${redis.blockWhenExhausted:true}")
    private boolean blockWhenExhausted;

    @Value("${redis.JmxEnabled:true}")
    private boolean jmxEnabled;

    @Bean
    public JedisPool jedisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 连接耗尽时是否阻塞, false报异常,true阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
        // 是否启用pool的jmx管理功能, 默认tru
        jedisPoolConfig.setJmxEnabled(jmxEnabled);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        return jedisPool;
    }
}
