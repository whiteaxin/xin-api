package com.zhx.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.apicommon.model.entity.UserInterfaceInfo;

public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    public boolean invokeInterfaceCount(long userId, long interfaceInfoId);

    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean b);
}
