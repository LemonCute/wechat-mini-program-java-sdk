package cn.jastz.wechat.app.wechat;

import cn.jastz.wechat.app.wechat.vo.Code2Session;

/**
 * @author zhiwen
 */
public interface MiniProgramOperates {
    String code2SessionUrl = "https://api.weixin.qq.com/sns/jscode2session?appid={appId}&secret={appSecret}&js_code={code}&grant_type=authorization_code";

    Code2Session code2Session(String code);
}
