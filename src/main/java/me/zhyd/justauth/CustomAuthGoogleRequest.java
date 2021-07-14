package me.zhyd.justauth;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthGoogleRequest;

/**
 * 测试：通过继承原 Request 实现 通过 token 获取用户信息的接口
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @date 2021-04-29 10:38
 * @since 1.0.0
 */
public class CustomAuthGoogleRequest extends AuthGoogleRequest {

    public CustomAuthGoogleRequest(AuthConfig config) {
        super(config);
    }

    @Override
    public AuthUser getUserInfo(AuthToken authToken) {
        return super.getUserInfo(authToken);
    }
}
