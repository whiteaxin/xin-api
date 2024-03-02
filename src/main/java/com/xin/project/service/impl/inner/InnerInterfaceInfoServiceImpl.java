package com.xin.project.service.impl.inner;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xin.apicommon.model.entity.InterfaceInfo;
import com.xin.apicommon.service.InnerInterfaceInfoService;
import com.xin.apicommon.common.ErrorCode;
import com.xin.apicommon.exception.BusinessException;
import com.xin.project.mapper.InterfaceInfoMapper;

import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {

	@Resource
	private InterfaceInfoMapper interfaceInfoMapper;

	@Override
	public InterfaceInfo getInvokeInterfaceInfo(String url, String method) {
		if (StrUtil.hasBlank(url, method)) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		LambdaQueryWrapper<InterfaceInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(InterfaceInfo::getUrl, url).eq(InterfaceInfo::getMethod, method);
		return interfaceInfoMapper.selectOne(lambdaQueryWrapper);
	}

}
