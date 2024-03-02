package com.xin.project.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.apicommon.model.dto.interfaceInfo.InterfaceInfoQueryRequest;
import com.xin.apicommon.model.dto.interfaceInfo.InterfaceInfoUpdateRequest;
import com.xin.apicommon.model.entity.InterfaceInfo;
import com.xin.apicommon.model.vo.interfaceInfo.InterfaceInfoVO;
import com.xin.xinapiclientsdk.client.XinApiClient;

import javax.servlet.http.HttpServletRequest;


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

    /**
     * 创建SDK客户端
     *
     * @param request 当前会话
     * @return SDK客户端
     */
    XinApiClient getXinApiClient(HttpServletRequest request);

    /**
     * 获取查询条件
     *
     * @param interfaceInfoQueryRequest
     * @return
     */
    QueryWrapper<InterfaceInfo> getQueryWrapper(InterfaceInfoQueryRequest interfaceInfoQueryRequest);

    /**
     * 获取接口信息封装
     *
     * @param interfaceInfo
     * @param request
     * @return
     */
    InterfaceInfoVO getInterfaceInfoVO(InterfaceInfo interfaceInfo, HttpServletRequest request);

    /**
     * 分页获取接口信息封装
     *
     * @param interfaceInfoPage
     * @param request
     * @return
     */
    Page<InterfaceInfoVO> getInterfaceInfoVOPage(Page<InterfaceInfo> interfaceInfoPage, HttpServletRequest request);


    /**
     * 修改接口信息
     *
     * @param interfaceInfoUpdateRequest 接口信息修改请求
     * @return 是否成功
     */
    boolean updateInterfaceInfo(InterfaceInfoUpdateRequest interfaceInfoUpdateRequest);

    /**
     * 根据用户ID 分页获取接口信息封装
     *
     * @param interfaceInfoPage 接口信息分页
     * @param request           当前会话
     * @return 接口信息分页
     */
    Page<InterfaceInfoVO> getInterfaceInfoVOByUserIdPage(Page<InterfaceInfo> interfaceInfoPage, HttpServletRequest request);
}
