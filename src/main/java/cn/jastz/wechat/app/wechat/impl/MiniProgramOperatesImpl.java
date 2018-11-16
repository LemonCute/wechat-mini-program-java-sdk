package cn.jastz.wechat.app.wechat.impl;

import cn.jastz.wechat.app.wechat.MiniProgramOperates;
import cn.jastz.wechat.app.wechat.vo.Code2Session;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhiwen
 */
public class MiniProgramOperatesImpl implements MiniProgramOperates {

    private RestTemplate restTemplate;
    private String appId;
    private String appSecret;

    public MiniProgramOperatesImpl(RestTemplate restTemplate, String appId, String appSecret) {
        this.restTemplate = restTemplate;
        this.appId = appId;
        this.appSecret = appSecret;
    }

    @Override
    public Code2Session code2Session(String code) {
        return restTemplate.getForObject(code2SessionUrl, Code2Session.class, appId, appSecret, code);
    }
}
