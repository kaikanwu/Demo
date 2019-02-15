package com.kk.dubbo.provider.quickstart;

import com.alibaba.dubbo.config.annotation.Service;
import com.kk.dubbo.ServiceAPI;
import org.springframework.stereotype.Component;

// component 注解：把这个类注册为 Bean
// service 注解：对外暴露这个服务
@Component
@Service(interfaceClass = ServiceAPI.class)
public class QuickstartServiceImpl implements ServiceAPI {
    @Override
    public String sendMessage(String message) {
        return "quickstarter-provider-message+" + message;
    }
}
