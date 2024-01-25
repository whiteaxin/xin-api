package com.zhx.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.apicommon.model.entity.InterfaceInfo;
import com.zhx.project.common.ErrorCode;
import com.zhx.project.exception.BusinessException;
import com.zhx.project.mapper.InterfaceInfoMapper;


import com.zhx.project.service.InterfaceInfoService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2023-10-24 16:38:44
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService {

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String name = interfaceInfo.getName();
        String description = interfaceInfo.getDescription();
        String url = interfaceInfo.getUrl();
        String requestHeader = interfaceInfo.getRequestHeader();
        String responseHeader = interfaceInfo.getResponseHeader();
        Integer status = interfaceInfo.getStatus();
        String method = interfaceInfo.getMethod();
        Long userId = interfaceInfo.getUserId();


        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(name, description, url, requestHeader, responseHeader, method)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }

        if (StringUtils.isNotBlank(name) && name.length() > 256) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名字过长");
        }

        if (StringUtils.isNotBlank(description) && description.length() > 512) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "描述过长");
        }

    }
}




