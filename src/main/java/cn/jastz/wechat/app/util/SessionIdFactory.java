package cn.jastz.wechat.app.util;

import java.util.UUID;

/**
 * @author zhiwen
 */
public class SessionIdFactory {

    public static String createSessionId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
