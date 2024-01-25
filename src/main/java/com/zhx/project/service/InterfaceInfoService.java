package com.zhx.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.apicommon.model.entity.InterfaceInfo;


/**
* @author DELL
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2023-10-24 16:38:44
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    /**
     * 校验接口信息
     * @param interfaceInfo
     * @param b
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean b);
}
