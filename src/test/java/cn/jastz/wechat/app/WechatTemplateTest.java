package cn.jastz.wechat.app;

import cn.jastz.wechat.app.wechat.WechatTemplate;
import cn.jastz.wechat.app.wechat.vo.Code2Session;
import me.jastz.common.json.JsonUtil;
import org.junit.Before;
import org.junit.Test;

/**
 * @author zhiwen
 */
public class WechatTemplateTest {

    private WechatTemplate wechatTemplate;

    @Before
    public void setUp() {
        wechatTemplate = new WechatTemplate();
        wechatTemplate.setMiniProgramAppId("wx45a98e4bd1acc3b9");
        wechatTemplate.setMiniProgramAppSecret("");
    }

    @Test
    public void code2Session() {
        Code2Session code2Session = wechatTemplate.miniProgramOperates().code2Session("071yXHLY0b49n12s0ZKY0GJiLY0yXHLW");
        System.out.println(JsonUtil.objectToPrettyJson(code2Session));
    }

    @Test
    public void jsonStrToObject() {
        String json = "{\"errcode\": 40163,\"errmsg\": \"code been used, hints: [ req_id: 8zmgTA0849ha60 ]\"}";
        Code2Session code2Session = JsonUtil.jsonToObject(json, Code2Session.class);
        System.out.println(JsonUtil.objectToPrettyJson(code2Session));
    }
}
