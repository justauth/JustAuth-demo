package me.zhyd.justauth;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.request.*;
import me.zhyd.oauth.utils.IpUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/2/19 9:28
 * @since 1.8
 */
@RestController
@RequestMapping("/oauth")
public class RestAuthController {

    /**
     * 存放state，授权、登录成功后，删除此次授权过程中生成的state
     */
    private static ConcurrentHashMap<String, String> stateBucket = new ConcurrentHashMap<>();

    @RequestMapping("/render/{source}")
    public void renderAuth(@PathVariable("source") String source, HttpServletResponse response) throws IOException {
        System.out.println("进入render：" + source);
        System.out.println("IP：" + IpUtils.getIp());
        AuthRequest authRequest = getAuthRequest(source);
        String authorizeUrl = authRequest.authorize();
        System.out.println(authorizeUrl);
        response.sendRedirect(authorizeUrl);
    }

    /**
     * oauth平台中配置的授权回调地址，以本项目为例，在创建github授权应用时的回调地址应为：http://127.0.0.1:8443/oauth/callback/github
     */
    @RequestMapping("/callback/{source}")
    public Object login(@PathVariable("source") String source, AuthCallback callback) {
        System.out.println("进入callback：" + source + " callback params：" + JSONObject.toJSONString(callback));
        System.out.println("IP：" + IpUtils.getIp());
        AuthRequest authRequest = getAuthRequest(source);
        AuthResponse response = authRequest.login(callback);
        System.out.println(JSONObject.toJSONString(response));

        // 登录成功后，清除state
        this.removeState(source);
        return response;
    }

    @RequestMapping("/revoke/{source}/{token}")
    public Object revokeAuth(@PathVariable("source") String source, @PathVariable("token") String token) throws IOException {
        AuthRequest authRequest = getAuthRequest(source);
        return authRequest.revoke(AuthToken.builder().accessToken(token).build());
    }

    /**
     * 根据具体的授权来源，获取授权请求工具类
     *
     * @param source
     * @return
     */
    private AuthRequest getAuthRequest(String source) {
        AuthRequest authRequest = null;
        switch (source) {
            case "dingtalk":
                authRequest = new AuthDingTalkRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://127.0.0.1:8443/oauth/callback/dingtalk")
                        .state(getState(source))
                        .build());
                break;
            case "baidu":
                authRequest = new AuthBaiduRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://127.0.0.1:8443/oauth/callback/baidu")
                        .state(getState(source))
                        .build());
                break;
            case "github":
                authRequest = new AuthGithubRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://127.0.0.1:8443/oauth/callback/github")
                        .state(getState(source))
                        .build());
                break;
            case "gitee":
                authRequest = new AuthGiteeRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://127.0.0.1:8443/oauth/callback/gitee")
                        .state(getState(source))
                        .build());
                break;
            case "weibo":
                authRequest = new AuthWeiboRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://127.0.0.1:8443/oauth/callback/weibo")
                        .state(getState(source))
                        .build());
                break;
            case "coding":
                authRequest = new AuthCodingRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://127.0.0.1:8443/oauth/callback/tencentCloud")
                        .state(getState(source))
                        .build());
                break;
            case "tencentCloud":
                authRequest = new AuthTencentCloudRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://127.0.0.1:8443/oauth/callback/tencentCloud")
                        .state(getState(source))
                        .build());
                break;
            case "oschina":
                authRequest = new AuthOschinaRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://127.0.0.1:8443/oauth/callback/oschina")
                        .state(getState(source))
                        .build());
                break;
            case "alipay":
                // 支付宝在创建回调地址时，不允许使用localhost或者127.0.0.1，所以这儿的回调地址使用的局域网内的ip
                authRequest = new AuthAlipayRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .alipayPublicKey("")
                        .redirectUri("http://127.0.0.1:8443/oauth/callback/alipay")
                        .state(getState(source))
                        .build());
                break;
            case "qq":
                authRequest = new AuthQqRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://127.0.0.1:8443/oauth/callback/qq")
                        .state(getState(source))
                        .build());
                break;
            case "wechat":
                authRequest = new AuthWeChatRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://127.0.0.1:8443/oauth/callback/wechat")
                        .state(getState(source))
                        .build());
                break;
            case "csdn":
                authRequest = new AuthCsdnRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://127.0.0.1:8443/oauth/callback/wechat")
                        .state(getState(source))
                        .build());
                break;
            case "taobao":
                authRequest = new AuthTaobaoRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://127.0.0.1:8443/oauth/callback/taobao")
                        .state(getState(source))
                        .build());
                break;
            case "google":
                authRequest = new AuthGoogleRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://127.0.0.1:8443/oauth/callback/google")
                        .state(getState(source))
                        .build());
                break;
            case "facebook":
                authRequest = new AuthFacebookRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("https://127.0.0.1:8443/oauth/callback/facebook")
                        .state(getState(source))
                        .build());
                break;
            case "douyin":
                authRequest = new AuthDouyinRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://127.0.0.1:8443/oauth/callback/douyin")
                        .state(getState(source))
                        .build());
                break;
            case "linkedin":
                authRequest = new AuthLinkedinRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://127.0.0.1:8443/oauth/callback/linkedin")
                        .state(getState(source))
                        .build());
                break;
            case "microsoft":
                authRequest = new AuthMicrosoftRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://127.0.0.1:8443/oauth/callback/microsoft")
                        .state(getState(source))
                        .build());
                break;
            case "mi":
                authRequest = new AuthMiRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://127.0.0.1:8443/oauth/callback/mi")
                        .state(getState(source))
                        .build());
                break;
            case "toutiao":
                authRequest = new AuthToutiaoRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://127.0.0.1:8443/oauth/callback/toutiao")
                        .state(getState(source))
                        .build());
                break;
        }
        if (null == authRequest) {
            throw new AuthException("未获取到有效的Auth配置");
        }
        return authRequest;
    }

    /**
     * state建议格式请参考：https://gitee.com/yadong.zhang/JustAuth/wikis/Q&A?sort_id=1513074#3-%E5%8D%87%E7%BA%A7%E5%88%B0180%E5%90%8E%E5%AF%B9%E4%BA%8Estate%E5%8F%82%E6%95%B0%E6%9C%89%E4%BB%80%E4%B9%88%E7%89%B9%E6%AE%8A%E8%A6%81%E6%B1%82%E5%90%97
     *
     * @param source
     * @return
     */
    private String getState(String source) {
        String key = (source + IpUtils.getIp()).replaceAll("\\.", "");
        System.out.println("state key：" + key);
        if (stateBucket.containsKey(key)) {
            System.out.println("当前请求存在可用的state：" + stateBucket.get(key));
            return stateBucket.get(key);
        }
        String state = (source + "_" + IpUtils.getIp() + "_" + RandomUtil.randomString(4)).replaceAll("\\.", "");
        System.out.println("当前请求不存在可用的state，生成新的state：" + state);
        stateBucket.put(key, state);
        return state;
    }

    /**
     * 登录成功后，清除state
     *
     * @param source
     */
    private void removeState(String source) {
        String key = (source + IpUtils.getIp()).replaceAll("\\.", "");
        System.out.println("授权登录成功，清空state: " + stateBucket.get(key));
        stateBucket.remove(key);
    }
}
