package org.apereo.cas.adaptors.redis.services;

import lombok.extern.slf4j.Slf4j;
import org.apereo.cas.services.RegisteredService;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Provides a template for redis operations.
 *
 * @author Misagh Moayyed
 * @since 5.2.0
 */
@Slf4j
public class RegisteredServiceRedisTemplate extends RedisTemplate<String, RegisteredService> {

    public RegisteredServiceRedisTemplate() {
        final RedisSerializer<String> string = new StringRedisSerializer();
        final var jdk = new JdkSerializationRedisSerializer();
        setKeySerializer(string);
        setValueSerializer(jdk);
        setHashKeySerializer(string);
        setHashValueSerializer(jdk);
    }

    public RegisteredServiceRedisTemplate(final RedisConnectionFactory connectionFactory) {
        this();
        setConnectionFactory(connectionFactory);
        afterPropertiesSet();
    }
}
