package com.zhx.project.constant;

public class RedisKey {
    private static final String BASE_KEY = "xin-api:";


    /**
     * 用户token存放
     */
    public static final String USER_TOKEN_STRING = "userToken:uid_%d";


    public static String getKey(String key, Object... objects) {
        return BASE_KEY + String.format(key, objects);
    }
}
