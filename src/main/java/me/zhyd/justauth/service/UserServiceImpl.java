package me.zhyd.justauth.service;

import me.zhyd.oauth.model.AuthUser;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @date 2020/6/27 22:41
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Map<String, AuthUser> BUCKET = new LinkedHashMap<>();

    @Override
    public AuthUser save(AuthUser user) {
        return BUCKET.put(user.getUuid(), user);
    }

    @Override
    public AuthUser getByUuid(String uuid) {
        return BUCKET.get(uuid);
    }

    @Override
    public List<AuthUser> listAll() {
        return new LinkedList<>(BUCKET.values());
    }

    @Override
    public void remove(String uuid) {
        BUCKET.remove(uuid);
    }
}
