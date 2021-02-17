package org.meco.auth.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.meco.system.client.UserClient;
import org.meco.system.dto.UserDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author ZhangYang
 * @date 2020/12/22
 */
@AllArgsConstructor
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserClient userClient;

//    private final RedisCacheManager redisCacheManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = null;
        final String userCacheName = "userName";

        // 先查询缓存
//        Cache cache = redisCacheManager.getCache(userCacheName);
//        if (cache != null) {
//            try {
//                user = (UserDto) Objects.requireNonNull(cache.get(username, R.class)).getData();
//            } catch (Exception ignored) {
//            }
//        }

        // 根据账号去数据库查询
        if (user == null) {
            user = userClient.findUserByUsername(username).getData();
            if (user == null) {
                return null;
            }
        }

        // 如果需要扩展信息，将 信息转为json保存到withUsername  JSONObject.toJSONString(systemUser)
        return User.withUsername(user.getUsername()).
                password(user.getPassword())
                // 关于权限的话，是用传入一个url 地址的数组，此处 不传，到微服务后通过redis获取,保证权限的实时性
                .authorities("null").build();

    }
}