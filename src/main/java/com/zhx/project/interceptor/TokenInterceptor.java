package com.zhx.project.interceptor;

import com.zhx.project.model.enums.HttpErrorEnum;
import com.zhx.project.service.LoginService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Optional;


@Component
public class TokenInterceptor implements HandlerInterceptor {

    public static final String HEADER_AUTHORIZATION = "Authorization";

    //Authorization参数的前缀
    public static final String AUTHORIZATION_SCHEMA = "Bearer ";
    public static final String UID = "uid";

    @Resource
    private LoginService loginService;

    //前置拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = getToken(request);
        //对token进行校验
        Long validUid = loginService.getValidUid(token);
        if(Objects.isNull(validUid)){ //用户没有登录
            //判断接口是否为public接口
            boolean isPublicURI = isPublicURI(request);
            if(!isPublicURI) { //不是public接口
                //401
                HttpErrorEnum.ACCESS_DENIED.sendHttpError(response);
                return false;//不能继续走了
            }
        }
        return true;
    }

    /**
     * 判断接口是否为public接口
     * @param request
     * @return
     */
    private static boolean isPublicURI(HttpServletRequest request) {
        String requestURI = request.getRequestURI();//   /capi/user/public/userInfo
        String[] split = requestURI.split("/");
        //分割后split[0] = "";
        boolean isPublicURI = split.length > 3 && "public".equals(split[3]);
        return isPublicURI;
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader(HEADER_AUTHORIZATION);

        // 获取请求头中的 Authorization 字段
        //header: Bearer skfjskjflsflsfsdf....
        //header有可能是空的
        return Optional.ofNullable(header)
                // 过滤掉不是以 "Bearer " 开头的 header
                .filter(h -> h.startsWith(AUTHORIZATION_SCHEMA))
                // 将 "Bearer " 替换为空字符串
                .map(h -> h.replaceFirst(AUTHORIZATION_SCHEMA, ""))
                // 如果没有符合条件的 header，则返回 null
                .orElse(null);
    }

}
