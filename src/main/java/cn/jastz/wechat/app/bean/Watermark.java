package cn.jastz.wechat.app.bean;

import java.sql.Timestamp;

/**
 * @author zhiwen
 */
public class Watermark {
    private String appid;
    private Timestamp timestamp;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
