package cn.jastz.wechat.app.util;


import cn.jastz.wechat.app.bean.WxaPhoneNumber;
import cn.jastz.wechat.app.crypto.Pkcs7Encoder;
import cn.jastz.wechat.app.bean.WxaUserInfo;
import me.jastz.common.json.JsonUtil;

import java.util.Base64;

/**
 * Created by zhiwen on 2017/3/17.
 */
public class WxaUtil {

    public final static String KEY = "wxa:session_%s";

    /**
     * 获取用户信息
     *
     * @param sessionKey
     * @param encryptedData
     * @param iv
     * @return
     */
    public static WxaUserInfo getUserInfo(String sessionKey, String encryptedData, String iv) {
        byte[] sessionKeyByte = Base64.getDecoder().decode(sessionKey.getBytes());
        byte[] encryptedDateByte = Base64.getDecoder().decode(encryptedData.getBytes());
        byte[] ivByte = Base64.getDecoder().decode(iv.getBytes());
        WxaUserInfo wxaUserInfo = JsonUtil.jsonToObject(new String(Pkcs7Encoder.decryptOfDiyIv(encryptedDateByte
                , sessionKeyByte, ivByte)), WxaUserInfo.class);
        return wxaUserInfo;
    }

    /**
     * 获取手机号
     *
     * @param sessionKey
     * @param encryptedData
     * @param iv
     * @return
     */
    public static WxaPhoneNumber getPhoneNumber(String sessionKey, String encryptedData, String iv) {
        byte[] sessionKeyByte = Base64.getDecoder().decode(sessionKey.getBytes());
        byte[] encryptedDateByte = Base64.getDecoder().decode(encryptedData.getBytes());
        byte[] ivByte = Base64.getDecoder().decode(iv.getBytes());
        WxaPhoneNumber wxaPhoneNumber = JsonUtil.jsonToObject(new String(Pkcs7Encoder.decryptOfDiyIv(encryptedDateByte
                , sessionKeyByte, ivByte)), WxaPhoneNumber.class);
        return wxaPhoneNumber;
    }

    public static String getSessionKey(String sessionId) {
        return String.format(KEY, sessionId);
    }

    public static void main(String[] args) {
        /*getUserInfo("oSrwE0ScvJLBQmBdywwscS3DP3bI"
                , "ETOcV1U59Ax9mvFvz1mNal3CWh8qeM/NKFnagfO7+e+8SLl/ChTsmpWNdSFKU9ql6o9K0AXEMQMu4RNg2fFMxsUadbj+yH34zznBTNXIDVc2vdj0dwb2B39HkhycKc4fDwbdZrsDrWeWh9E6h84DI7KbH5AXkKVkLRcFxiSeytpnrmYSrDHT0roj1e2EtwSpuaZcwXBKYQEn/2+sZmjUH9Xd2u/t2aHO2ujKQ21vacWsUplmvw0W5WShd+2PB9U4oBNIAkUXh+PMu2hVOpv8bHwslzKtk7Mut5TeHJ5WbkHfHziTtTZCporBRmxAfiWuZZ66DrCyDyG0fKXpUyQWCo+EEObMncZJFmGG56bjqNCd59jA++69b9YcYnlkFRIGi/B+37vq/pGJfBH29+xC2UsQ7aYsPTK723MxNeFSGVvQOXf0tlu4goETqtkhLSRZjz+VoA0iAeofGaU5vmPUjzMHT3Ee4zZ097tEVcHjQI4="
                , "7aHJHWPpUPvDvkucdqdo7Q==");
        WxaUserInfo wxaUserInfo = JsonUtils.parse("{\"openId\":\"oLr0J0RSjn1U9VMRyL_pgf0o354Q\",\"nickName\":\"疯狂的乌龟\",\"gender\":1,\"language\":\"zh_CN\",\"city\":\"Shenzhen\",\"province\":\"Guangdong\",\"country\":\"CN\",\"avatarUrl\":\"http://wx.qlogo.cn/mmopen/vi_32/RQCuDQnImhtBYzc46rTYFeJLn7BVpWUB8tvu8Ep2JugA0Q80mfnHLK7ACmhGCricKoiaKGhv23eGCFPbukuGcAsA/0\",\"unionId\":\"okyCzwSdQoLYMoYIIM9rBucb9GSU\",\"watermark\":{\"timestamp\":1490346644,\"appid\":\"wx508d2d28f5c722f1\"}}", WxaUserInfo.class);
        System.out.println(wxaUserInfo);*/

        WxaPhoneNumber wxaPhoneNumber = getPhoneNumber("RWhPDM4zHiaAFZA0Z5qKIg==", "0dIreEhAB2NdeuUvpNCABu9dVBpbCsUtQzZeGJkIW9KQ8cY7h9Wp347SzAOduhpiw6ej678wLDP1E7UrUPhxYidWc+CeJsZfPq6TkVphA0cWhVGeIpYCKLSEn27S1nkOcw1O2mycHGM3sQCY5uGJqI0OejwoUBpom3YLXdPTcsaUPvfnjGtTDKrXTmgWtX/xNq7nTPvOH/d3FiEAHcL+2w==", "oaVg5MIUwI4QNHSNMr8/ng==");
        System.out.println(JsonUtil.objectToPrettyJson(wxaPhoneNumber));
    }
}
