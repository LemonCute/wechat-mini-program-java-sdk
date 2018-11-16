package cn.jastz.wechat.app.bean;

/**
 * @author zhiwen
 */
public class WxaSessionValue {
    private String sessionKey;
    private String openId;
    private String unionId;
    private int uid;

    public WxaSessionValue() {
    }

    public WxaSessionValue(String sessionKey, String openId) {
        this.sessionKey = sessionKey;
        this.openId = openId;
    }

    public WxaSessionValue(String sessionKey, String openId, String unionId) {
        this.sessionKey = sessionKey;
        this.openId = openId;
        this.unionId = unionId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    @Override
    public String toString() {
        return "{\"_class\":\"WxaSessionValue\", " +
                "\"sessionKey\":" + (sessionKey == null ? "null" : "\"" + sessionKey + "\"") + ", " +
                "\"openId\":" + (openId == null ? "null" : "\"" + openId + "\"") + ", " +
                "\"uid\":\"" + uid + "\"" +
                "}";
    }
}
