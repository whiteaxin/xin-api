package com.zhx.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.apicommon.model.entity.UserInterfaceInfo;
import com.zhx.project.common.ErrorCode;
import com.zhx.project.exception.BusinessException;
import com.zhx.project.mapper.UserInterfaceInfoMapper;
import com.zhx.project.service.UserInterfaceInfoService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【user_interface_info(用户接口关系表)】的数据库操作Service实现
* @createDate 2023-11-02 11:17:14
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService {


    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean b) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Long interfaceInfoId = userInterfaceInfo.getInterfaceInfoId();
        Long userId = userInterfaceInfo.getUserId();
        Integer leftNum = userInterfaceInfo.getLeftNum();
        Integer totalNum = userInterfaceInfo.getTotalNum();
        Integer status = userInterfaceInfo.getStatus();

        // 创建时，所有参数必须非空
        if (b) {
            if (ObjectUtils.anyNull(interfaceInfoId, userId)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }

        if(leftNum == null || leftNum < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"leftNum不能小于0");
        }

        if(totalNum == null || totalNum < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"totalNum不能小于0");
        }
    }

    //todo 添加事务，添加锁
    @Override
    public boolean invokeInterfaceCount(long userId, long interfaceInfoId) {
        if (userId <= 0 || interfaceInfoId <= 0) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        LambdaUpdateWrapper<UserInterfaceInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(UserInterfaceInfo::getUserId, userId)
                .eq(UserInterfaceInfo::getInterfaceInfoId, interfaceInfoId)
                .gt(UserInterfaceInfo::getLeftNum, 0)
                .setSql("left_num = left_num -1, total_num = total_num + 1");

        return update(updateWrapper);
    }
}




