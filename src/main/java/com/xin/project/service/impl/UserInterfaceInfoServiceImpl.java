package com.xin.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.apicommon.constant.CommonConstant;
import com.xin.apicommon.model.dto.userInterfaceInfo.UserInterfaceInfoQueryRequest;
import com.xin.apicommon.model.entity.UserInterfaceInfo;
import com.xin.apicommon.common.ErrorCode;
import com.xin.apicommon.exception.BusinessException;
import com.xin.project.mapper.UserInterfaceInfoMapper;
import com.xin.project.service.UserInterfaceInfoService;
import com.xin.project.utils.SqlUtils;
import com.xin.project.utils.ThrowUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author DELL
* @description 针对表【user_interface_info(用户接口关系表)】的数据库操作Service实现
* @createDate 2023-11-02 11:17:14
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long userId = userInterfaceInfo.getUserId();
        Long interfaceInfoId = userInterfaceInfo.getInterfaceInfoId();
        Integer totalNum = userInterfaceInfo.getTotalNum();
        Integer leftNum = userInterfaceInfo.getLeftNum();

        List<UserInterfaceInfo> list = this.lambdaQuery()
                .eq(UserInterfaceInfo::getUserId, userId)
                .eq(UserInterfaceInfo::getInterfaceInfoId, interfaceInfoId)
                .list();
        if (!list.isEmpty()) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "该用户已经拥有该接口");
        }

        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(userId == null || interfaceInfoId == null, ErrorCode.PARAMS_ERROR);
        }
    }


    /**
     * 获取查询包装类
     *
     * @param interfaceInfoQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<UserInterfaceInfo> getQueryWrapper(UserInterfaceInfoQueryRequest interfaceInfoQueryRequest) {

        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        if (interfaceInfoQueryRequest == null) {
            return queryWrapper;
        }

        String searchText = interfaceInfoQueryRequest.getSearchText();
        Long id = interfaceInfoQueryRequest.getId();
        Long userId = interfaceInfoQueryRequest.getUserId();
        Long interfaceInfoId = interfaceInfoQueryRequest.getInterfaceInfoId();
        Integer totalNum = interfaceInfoQueryRequest.getTotalNum();
        Integer leftNum = interfaceInfoQueryRequest.getLeftNum();
        Integer status = interfaceInfoQueryRequest.getStatus();
        String sortField = interfaceInfoQueryRequest.getSortField();
        String sortOrder = interfaceInfoQueryRequest.getSortOrder();

        // 拼接查询条件
        if (StringUtils.isNotBlank(searchText)) {
            queryWrapper.like("name", searchText);
        }
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(totalNum), "totalNum", totalNum);
        queryWrapper.eq(ObjectUtils.isNotEmpty(leftNum), "leftNum", leftNum);
        queryWrapper.eq(ObjectUtils.isNotEmpty(interfaceInfoId), "interfaceInfoId", interfaceInfoId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(status), "status", status);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq("isDelete", false);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }


    @Override
    public Page<UserInterfaceInfo> getUserInterfaceInfoVOPage(Page<UserInterfaceInfo> userInterfaceInfoPage, HttpServletRequest request) {
        List<UserInterfaceInfo> interfaceInfoList = userInterfaceInfoPage.getRecords();
        Page<UserInterfaceInfo> interfaceInfoVOPage = new Page<>(userInterfaceInfoPage.getCurrent(), userInterfaceInfoPage.getSize(), userInterfaceInfoPage.getTotal());
        if (CollectionUtils.isEmpty(interfaceInfoList)) {
            return interfaceInfoVOPage;
        }
        interfaceInfoVOPage.setRecords(interfaceInfoList);
        return interfaceInfoVOPage;
    }

    @Override
    public List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit) {
        return userInterfaceInfoMapper.listTopInvokeInterfaceInfo(limit);
    }
}




