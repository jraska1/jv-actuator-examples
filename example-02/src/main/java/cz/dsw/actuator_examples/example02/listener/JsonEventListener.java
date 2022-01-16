package cz.dsw.actuator_examples.example02.listener;

import cz.dsw.actuator_examples.example02.event.CustomEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
@EnableAsync
@Profile("listener-redis")
public class JsonEventListener {

    private static final Logger logger = LoggerFactory.getLogger(JsonEventListener.class);

    @Autowired private StringRedisTemplate redisTemplate;
    @Autowired private Function<Object, String> toJson;

    @Async
    @EventListener
    public void handleEvent(CustomEvent event) {

        String key = UUID.randomUUID().toString();
        String value = toJson.apply(event);

        redisTemplate.opsForValue().append(key, value);
        redisTemplate.expire(key, 60, TimeUnit.SECONDS);
        logger.info("Custom Event {} written to REDIS: {}\n{}", event.getEvent(), key, value);
    }
}
