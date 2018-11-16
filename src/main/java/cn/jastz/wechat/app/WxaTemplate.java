package cn.jastz.wechat.app;

import cn.jastz.wechat.app.session.WxaSession;
import cn.jastz.wechat.app.wechat.WechatTemplate;
import cn.jastz.wechat.app.wechat.vo.Code2Session;
import cn.jastz.wechat.app.bean.WxaSessionValue;
import cn.jastz.wechat.app.util.SessionIdFactory;
import cn.jastz.wechat.app.util.WxaUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author zhiwen
 */
public class WxaTemplate {

    @Autowired
    private WechatTemplate wechatTemplate;

    @Autowired
    private WxaSession<String, WxaSessionValue> wxaSession;


    /**
     * 根据code及业务Id 获取 session
     *
     * @param code
     * @param isDev 取值：true 是开发环境，false 正式环境
     * @return
     */
    public String getSession(String code, boolean isDev) {
        String sessionId = SessionIdFactory.createSessionId();
        String openid = "";
        String sessionKey = "";
        String unionId = "";
        if (!isDev) {
            Code2Session session = wechatTemplate.miniProgramOperates().code2Session(code);
            if (session.getErrcode() != 0) {
                throw new RuntimeException(session.getErrmsg());
            }
            openid = session.getOpenid();
            sessionKey = session.getSession_key();
            unionId = session.getUnionid();
        }
        wxaSession.put(WxaUtil.getSessionKey(sessionId)
                , new WxaSessionValue(sessionKey, openid, unionId));
        return sessionId;
    }

    /**
     * 关联Uid到SessionId
     *
     * @param uid
     * @param sessionId
     * @return
     */
    public void setSessionUid(int uid, String sessionId) {
        WxaSessionValue wxaSessionValue = wxaSession.get(WxaUtil.getSessionKey(sessionId));
        wxaSessionValue.setUid(uid);
        wxaSession.put(WxaUtil.getSessionKey(sessionId), wxaSessionValue);
    }

    /**
     * 获取当前业务平台登入的用户
     *
     * @param sessionId
     * @return
     */
    public int getSessionUid(String sessionId) throws Exception {
        WxaSessionValue wxaSessionValue = wxaSession.get(WxaUtil.getSessionKey(sessionId));
        if (wxaSessionValue == null) {
            throw new UserNotLoginException();
        }
        return wxaSessionValue.getUid();
    }

    public String getWechatSessionKey(String sessionId) throws Exception {
        WxaSessionValue wxaSessionValue = wxaSession.get(WxaUtil.getSessionKey(sessionId));
        if (wxaSessionValue == null) {
            throw new UserNotLoginException();
        }
        return wxaSessionValue.getSessionKey();
    }

    public String getSessionIdByUid(int uid) {
        String key = null;
        Map<String, WxaSessionValue> values = wxaSession.allValue();
        for (String item : wxaSession.allValue().keySet()) {
            if (uid == values.get(item).getUid()) {
                key = item;
                break;
            }
        }
        if (key == null) {
            return null;
        }
        String[] split = key.split(":");
        if (split.length == WxaUtil.KEY.split(":").length) {
            return split[split.length - 1].replace("session_", "");
        }
        return null;
    }

    /**
     * 移除所有已登入的用户
     *
     * @param uid
     */
    public void removeByUid(int uid) {
        Map<String, WxaSessionValue> values = wxaSession.allValue();
        for (String key : wxaSession.allValue().keySet()) {
            if (uid == values.get(key).getUid()) {
                wxaSession.removeByKey(key);
            }
        }
    }

    public WxaSessionValue getWxaSessionValueBySessionId(String sessionId) {
        return wxaSession.get(WxaUtil.getSessionKey(sessionId));
    }

}
