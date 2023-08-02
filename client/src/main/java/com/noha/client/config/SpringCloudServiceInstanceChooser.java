package com.noha.client.config;


import com.github.lianjiatech.retrofit.spring.boot.core.ServiceInstanceChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.net.URI;

@Service
public class SpringCloudServiceInstanceChooser implements ServiceInstanceChooser {

    private LoadBalancerClient loadBalancerClient;

    @Autowired
    public SpringCloudServiceInstanceChooser(LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient;
    }

    /**
     * Chooses a ServiceInstance URI from the LoadBalancer for the specified service.
     *
     * @param serviceId The service ID to look up the LoadBalancer.
     * @return Return the uri of ServiceInstance
     */
    @Override
    public URI choose(String serviceId) {
        ServiceInstance serviceInstance = loadBalancerClient.choose(serviceId);
        Assert.notNull(serviceInstance, "can not found service instance! serviceId=" + serviceId);
        return serviceInstance.getUri();
    }
}
