package cn.jastz.wechat.app.session;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhiwen
 */
public interface WxaSession<K, V> {

    void put(K key, V value);

    void put(K key, V value, long timeout, TimeUnit timeUnit);

    V get(K key);

    Map<K, V> allValue();

    void removeByKey(K key);

}
