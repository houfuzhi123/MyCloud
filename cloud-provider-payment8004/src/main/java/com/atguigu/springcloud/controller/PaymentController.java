package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @auther zzyy
 * @create 2020-02-19 14:16
 */
@RestController
@Slf4j
public class PaymentController
{
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/payment/zk")
    public String paymentzk()
    {
        return "springcloud with zookeeper: "+serverPort+"\t"+ UUID.randomUUID().toString();
    }
    @GetMapping(value = "/payment/discover")
    public Object Discover(){
        List<String> services = discoveryClient.getServices();
        for (String s : services) {
            log.info("******element"+s);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance i : instances) {
            log.info(i.getInstanceId()+"\t"+i.getHost()+"\t"+i.getPort()+"\t"+i.getUri());
        }
        return this.discoveryClient;
    }
}
