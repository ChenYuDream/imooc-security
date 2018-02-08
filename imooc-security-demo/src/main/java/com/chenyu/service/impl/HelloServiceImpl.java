package com.chenyu.service.impl;

import com.chenyu.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author yu_chen
 * @create 2018-02-08 18:28
 **/
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String greet(String name) {
        System.out.println(name);
        return null;
    }
}
