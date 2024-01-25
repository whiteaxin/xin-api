package com.zhx.project.model.vo.user.resp;

import com.xin.apicommon.model.vo.UserVO;
import lombok.Data;

@Data
public class LoginResp extends UserVO {
    private String token;
}
