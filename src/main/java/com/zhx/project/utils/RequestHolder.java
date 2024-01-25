package com.zhx.project.utils;



/**
 * 请求上下文
 */
public class RequestHolder {
    private static final ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void set(Long uid) {
        // 将请求信息设置到请求持有者中
        requestHolder.set(uid);
    }


    public static Long get() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}
