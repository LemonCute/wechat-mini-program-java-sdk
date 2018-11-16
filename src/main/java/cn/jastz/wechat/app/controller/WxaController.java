package cn.jastz.wechat.app.controller;

import cn.jastz.wechat.app.bean.LoginForm;

/**
 * @author zhiwen
 */
public interface WxaController {

    /**
     * 根据 code 获取 session
     *
     * @param code
     * @param isDev 取值：true 是开发环境，false 正式环境
     * @return
     */
    String getSession(String code, boolean isDev);

    /**
     * 根据 wx.getUserInfo(OBJECT) 获得用户信息进行登入
     *
     * @param loginForm
     */
    void login(LoginForm loginForm);
}
