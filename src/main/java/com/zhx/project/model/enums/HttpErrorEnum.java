package com.zhx.project.model.enums;

import cn.hutool.http.ContentType;
import com.google.common.base.Charsets;
import com.zhx.project.common.ResultUtils;
import com.zhx.project.exception.BusinessException;
import com.zhx.project.utils.JsonUtils;
import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public enum HttpErrorEnum {
    ACCESS_DENIED(401,"登录失效，请重新登录");

//    HttpErrorEnum(Integer httpCode, String desc) {
//        this.httpCode = httpCode;
//        this.desc = desc;
//    }

    private Integer httpCode;
    private String desc;

    public void sendHttpError(HttpServletResponse response) throws IOException {
        response.setStatus(httpCode);
        response.setContentType(ContentType.JSON.toString(Charsets.UTF_8));
        response.getWriter().write(JsonUtils.toStr(ResultUtils.error(httpCode,desc)));

    }
}