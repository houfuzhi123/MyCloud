package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBanlancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
    private static final String Payment_URL="http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment)
    {
        return restTemplate.postForObject(Payment_URL +"/payment/create",payment,CommonResult.class);

    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id)
    {
        return restTemplate.getForObject(Payment_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get2/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id)
    {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(Payment_URL + "/payment/get/" + id, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult<>(444,"操作失败");
        }
    }
    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB()
    {
        return restTemplate.getForObject(Payment_URL+"/payment/lb",String.class);
    }

    @GetMapping(value = "/consumer/payment/zipkin")
    public String paymentZipkin()
    {
        return restTemplate.getForObject(Payment_URL+"/payment/zipkin",String.class);
    }
}
