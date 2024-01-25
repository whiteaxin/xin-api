package com.zhx.project.service.impl;


import com.zhx.project.constant.RedisKey;
import com.zhx.project.service.LoginService;
import com.zhx.project.utils.JwtUtils;
import com.zhx.project.utils.RedisUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


@Service
public class LoginServiceImpl implements LoginService {

    /**
     * token过期天数
     */
    public static final int TOKEN_EXPIRE_DAYS = 3;
    @Resource
    private JwtUtils jwtUtils;

    @Override
    @Async  //异步执行 使用自定义线程池
    public void renewalTokenIfNecessary(String token) {
        Long validUid = getValidUid(token);
        String userTokenKey = getUserTokenKey(validUid);
        Long expireDays = RedisUtils.getExpire(userTokenKey, TimeUnit.DAYS);
        if(expireDays == -2){//不存在的key
            return;
        }
        if(expireDays < 1){
            RedisUtils.expire(userTokenKey,TOKEN_EXPIRE_DAYS,TimeUnit.DAYS);
        }
    }

    @Override
    public String login(Long id) {
        String token = jwtUtils.createToken(id);
        RedisUtils.set(getUserTokenKey(id), token, TOKEN_EXPIRE_DAYS, TimeUnit.DAYS);
        return token;
    }

    @Override
    public Long getValidUid(String token) {
        Long uid = jwtUtils.getUidOrNull(token);
        if (Objects.isNull(uid)) {
            return null;
        }

        //判断是否在有效期内
        String oldToken = RedisUtils.getStr(getUserTokenKey(uid));

        //如果我们在其他电脑上登录了，原本的token：123变成了456，
        //拿着123的token仍然能解析出uid，并且还在有效期（456），
        //所以我们呢要先判断一下请求的token和redis中的token是否一致
        return Objects.equals(oldToken, token) ? uid : null;
    }

    //根据uid获取到key
    private String getUserTokenKey(Long uid) {
        return RedisKey.getKey(RedisKey.USER_TOKEN_STRING, uid);
    }
}
