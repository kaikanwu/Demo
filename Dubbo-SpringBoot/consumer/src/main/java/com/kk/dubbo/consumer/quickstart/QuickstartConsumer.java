package com.kk.dubbo.consumer.quickstart;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kk.dubbo.ServiceAPI;
import org.springframework.stereotype.Component;

@Component
public class QuickstartConsumer {


    @Reference(interfaceClass = ServiceAPI.class)
    ServiceAPI serviceAPI;


    public void sendMessage(String message){
        System.out.println(serviceAPI.sendMessage(message));
    }




}
