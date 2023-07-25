package com.noha.cloudadmin.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {


    @GetMapping(value = "/nacos/getString/{id}")
    public String getString(@PathVariable("id") Integer id) {
        return "远程调用成功 id为"+id;
    }
}
