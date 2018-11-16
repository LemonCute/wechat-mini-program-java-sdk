package cn.jastz.wechat.app.session.impl;


import cn.jastz.wechat.app.session.WxaSession;
import cn.jastz.wechat.app.bean.WxaSessionValue;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhiwen
 */
public class GuavaWxaSession implements WxaSession<String, WxaSessionValue> {
    protected static final Cache<String, WxaSessionValue> CACHE;

    static {
        CACHE = CacheBuilder.newBuilder().maximumSize(150000L).expireAfterWrite(60 * 24 * 7L, TimeUnit.MINUTES).build();
    }

    @Override
    public void put(String key, WxaSessionValue value) {
        CACHE.put(key, value);
    }

    @Override
    public WxaSessionValue get(String key) {
        return CACHE.getIfPresent(key);
    }

    @Override
    public Map<String, WxaSessionValue> allValue() {
        return CACHE.asMap();
    }

    @Override
    public void removeByKey(String key) {
        CACHE.invalidate(key);
    }


}
