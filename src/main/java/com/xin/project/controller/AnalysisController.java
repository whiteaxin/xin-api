package com.xin.project.controller;


import com.xin.apicommon.common.BaseResponse;
import com.xin.apicommon.common.ErrorCode;
import com.xin.apicommon.common.ResultUtils;
import com.xin.apicommon.constant.UserConstant;
import com.xin.apicommon.exception.BusinessException;
import com.xin.apicommon.model.entity.InterfaceInfo;
import com.xin.apicommon.model.entity.UserInterfaceInfo;
import com.xin.apicommon.model.vo.interfaceInfo.InterfaceInfoVO;
import com.xin.project.annotation.AuthCheck;
import com.xin.project.service.InterfaceInfoService;
import com.xin.project.service.UserInterfaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 统计分析接口
 */
@RestController
@RequestMapping("/analysis")
@Slf4j
public class AnalysisController {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @GetMapping("/top/interface/invoke")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<InterfaceInfoVO>> listTopInvokeInterfaceInfo() {
        // 查询调用次数前3名的接口
        List<UserInterfaceInfo> userInterfaceInfoList = userInterfaceInfoService.listTopInvokeInterfaceInfo(3);
        if (userInterfaceInfoList.isEmpty()) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "接口信息不存在");
        }
        // 根据接口id分组
        Map<Long, List<UserInterfaceInfo>> interfaceInfoIdObjMap = userInterfaceInfoList.stream()
                .collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));
        // 查询所有接口id的接口信息
        List<InterfaceInfo> list = interfaceInfoService.lambdaQuery()
                .in(InterfaceInfo::getId, interfaceInfoIdObjMap.keySet())
                .list();
        if (list.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口信息不存在");
        }
        // 组装返回结果
        List<InterfaceInfoVO> result = list.stream().map(interfaceInfo -> {
            InterfaceInfoVO interfaceInfoVO = InterfaceInfoVO.objToVo(interfaceInfo);
            interfaceInfoVO.setTotalNum(interfaceInfoIdObjMap.get(interfaceInfo.getId()).get(0).getTotalNum());
            return interfaceInfoVO;
        }).collect(Collectors.toList());

        return ResultUtils.success(result);
    }

}
