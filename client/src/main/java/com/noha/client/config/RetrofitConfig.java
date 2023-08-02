package com.noha.client.config;


import com.github.lianjiatech.retrofit.spring.boot.core.ServiceInstanceChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetrofitConfig {
    @Bean
    @Autowired
    public ServiceInstanceChooser serviceInstanceChooser(LoadBalancerClient loadBalancerClient) {
        return new SpringCloudServiceInstanceChooser(loadBalancerClient);
    }
}
