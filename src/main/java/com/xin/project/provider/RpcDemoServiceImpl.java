package com.xin.project.provider;
import com.xin.apicommon.service.RpcDemoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;

//服务提供者
@DubboService
public class RpcDemoServiceImpl implements RpcDemoService {
	@Override
	public String sayHello(String name) {
		return "Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress();
	}
}