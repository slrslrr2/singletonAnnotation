package com.example.demo.annotation.component.service.impl;

import com.example.demo.annotation.component.service.ComponentService;
import org.springframework.stereotype.Component;

@Component
public class ComponentServiceImpl implements ComponentService {
    @Override
    public void join(String memberId) {
        System.out.println("ComponentServiceImpl 도착!");
        System.out.println("memberId = " + memberId);
    }
}
