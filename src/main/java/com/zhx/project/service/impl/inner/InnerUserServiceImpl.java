package com.zhx.project.service.impl.inner;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xin.apicommon.model.entity.User;
import com.xin.apicommon.service.InnerUserService;
import com.zhx.project.common.ErrorCode;
import com.zhx.project.exception.BusinessException;
import com.zhx.project.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @author: 玄
 * @date: 2023/2/6
 */

@DubboService
public class InnerUserServiceImpl implements InnerUserService {

	@Resource
	private UserMapper userMapper;


	@Override
	public User getInvokeUser(String accessKey) {
		if (StrUtil.isBlank(accessKey)) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(User::getAccessKey, accessKey);
		return userMapper.selectOne(lambdaQueryWrapper);
	}
}