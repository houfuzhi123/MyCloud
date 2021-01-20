package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderZkController {
    public static  final String INVOKE_URL="http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/zk")
    public String PaymentInfo(){
        String result=restTemplate.getForObject(INVOKE_URL+"/payment/zk",String.class);
        return result;
    }
    @GetMapping(value = "/consumer/payment/discover")
    public Object Discover(){
        String result=restTemplate.getForObject(INVOKE_URL+"/payment/discover",String.class);
        return result;
    }
}
