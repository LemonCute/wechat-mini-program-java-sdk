package cn.jastz.wechat.app.session.impl;

import cn.jastz.wechat.app.bean.WxaSessionValue;
import cn.jastz.wechat.app.session.WxaSession;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhiwen
 */
public class ConcurrentMapWxaSession implements WxaSession<String, WxaSessionValue> {
    private final ConcurrentHashMap concurrentMap = new ConcurrentHashMap();

    @Override
    public void put(String key, WxaSessionValue v) {
        System.out.println("Thread name " + Thread.currentThread().getName());
        concurrentMap.putIfAbsent(key, v);
    }

    @Override
    public WxaSessionValue get(String key) {
        return (WxaSessionValue) concurrentMap.get(key);
    }

    @Override
    public Map<String, WxaSessionValue> allValue() {
        return Maps.newHashMap(concurrentMap);
    }

    @Override
    public void removeByKey(String key) {
        concurrentMap.remove(key);
    }
}
