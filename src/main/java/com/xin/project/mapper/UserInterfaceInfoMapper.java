package com.xin.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xin.apicommon.model.entity.UserInterfaceInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author DELL
* @description 针对表【user_interface_info(用户接口关系表)】的数据库操作Mapper
*/
@Mapper
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {
    /**
     * 获取接口调用排名前 n 的接口信息
     *
     * @param limit 前几名
     * @return List<InterfaceInfoVO>
     */
    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);
}




