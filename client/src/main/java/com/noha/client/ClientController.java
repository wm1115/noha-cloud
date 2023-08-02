package com.noha.client;

import com.noha.client.httpapi.HttpApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientController {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpApi httpApi;

    @GetMapping("/clientTest")
    public String clientGetString() {
//        return restTemplate.getForObject("http://admin/nacos/getString/"+2,String.class);

        return httpApi.getString(1);

    }
}
