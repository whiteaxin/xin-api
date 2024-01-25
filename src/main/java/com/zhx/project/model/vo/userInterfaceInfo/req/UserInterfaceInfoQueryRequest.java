package com.zhx.project.model.vo.userInterfaceInfo.req;

import com.baomidou.mybatisplus.annotation.TableField;

import com.zhx.project.common.PageRequest;
import lombok.Data;



@Data
public class UserInterfaceInfoQueryRequest extends PageRequest {
    /**
     * id
     */
    private Long id;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 接口 id
     */
    private Long interfaceInfoId;

    /**
     * 总调用次数
     */
    private Integer totalNum;

    /**
     * 剩余调用次数
     */
    private Integer leftNum;

    /**
     * 0-正常，1-禁用
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
