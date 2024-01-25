package com.zhx.project.model.vo.interfaceInfo.req;

import lombok.Data;

import java.io.Serializable;


/**
 * 调用接口参数
 *
 * @author xuan
 */
@Data
public class InvokeInterfaceRequest implements Serializable {

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 请求参数
	 */
	private String requestParams;

}