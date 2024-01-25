package com.zhx.project.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xin.apicommon.model.entity.UserInterfaceInfo;
import com.xin.apicommon.service.InnerUserInterfaceInfoService;
import com.zhx.project.common.ErrorCode;
import com.zhx.project.exception.BusinessException;
import com.zhx.project.mapper.UserInterfaceInfoMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

	@Resource
	private UserInterfaceInfoMapper userInterfaceInfoMapper;

	@Override
	public boolean hasInvokeNum(long userId, long interfaceInfoId) {
		if (userId <= 0 || interfaceInfoId <= 0) {
			throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
		}

		LambdaQueryWrapper<UserInterfaceInfo> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(UserInterfaceInfo::getUserId, userId)
				.eq(UserInterfaceInfo::getInterfaceInfoId, interfaceInfoId)
				.gt(UserInterfaceInfo::getLeftNum, 0);

		UserInterfaceInfo userInterfaceInfo = userInterfaceInfoMapper.selectOne(queryWrapper);
		return userInterfaceInfo != null;
	}

	@Override
	public boolean invokeInterfaceCount(long userId, long interfaceInfoId) {
		if (userId <= 0 || interfaceInfoId <= 0) {
			throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
		}

		LambdaUpdateWrapper<UserInterfaceInfo> updateWrapper = new LambdaUpdateWrapper<>();
		updateWrapper.eq(UserInterfaceInfo::getUserId, userId)
				.eq(UserInterfaceInfo::getInterfaceInfoId, interfaceInfoId)
				.gt(UserInterfaceInfo::getLeftNum, 0)
				.setSql("leftNum = leftNum -1, totalNum = totalNum + 1");

		int updateCount = userInterfaceInfoMapper.update(null, updateWrapper);
		return updateCount > 0;
	}

}
