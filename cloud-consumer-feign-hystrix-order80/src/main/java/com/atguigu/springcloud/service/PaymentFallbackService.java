package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;


@Component
public class PaymentFallbackService implements PaymentHystrixService  //此类是用来处理所有类中方法的服务降级问题
{
    @Override
    public String paymentInfo_OK(Integer id)
    {
        return "-----PaymentFallbackService fall back-paymentInfo_OK ,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id)
    {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut ,o(╥﹏╥)o";
    }

    @Override
    public String paymentCircuitBreaker(Integer id) {
        return null;
    }
}
