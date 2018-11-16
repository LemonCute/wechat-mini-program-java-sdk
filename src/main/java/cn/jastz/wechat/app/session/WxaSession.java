package cn.jastz.wechat.app.session;

import java.util.Map;

/**
 * @author zhiwen
 */
public interface WxaSession<K, V> {

    void put(K key, V value);

    V get(K key);

    Map<K, V> allValue();

    void removeByKey(K key);

}
