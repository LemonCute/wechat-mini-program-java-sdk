package cn.jastz.wechat.app.session.impl;

import cn.jastz.wechat.app.session.WxaSession;
import cn.jastz.wechat.app.bean.WxaSessionValue;
import cn.jastz.wechat.app.util.WxaUtil;
import com.google.common.collect.Maps;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zhiwen
 */
public class RedisWxaSession implements WxaSession<String, WxaSessionValue> {

    private RedisTemplate<String, WxaSessionValue> redisTemplate;

    public RedisWxaSession(RedisTemplate<String, WxaSessionValue> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void put(String key, WxaSessionValue value) {
        redisTemplate.opsForValue().set(key, value, 60 * 24 * 7L, TimeUnit.MINUTES);
    }

    @Override
    public void put(String key, WxaSessionValue value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout,timeUnit);
    }

    @Override
    public WxaSessionValue get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Map<String, WxaSessionValue> allValue() {
        Map<String, WxaSessionValue> map = Maps.newHashMap();
        Set<String> sessionKeys = redisTemplate.keys(String.format(WxaUtil.KEY, "*"));
        sessionKeys.forEach(key -> map.put(key, redisTemplate.opsForValue().get(key)));
        return map;
    }

    @Override
    public void removeByKey(String key) {
        redisTemplate.delete(key);
    }
}
