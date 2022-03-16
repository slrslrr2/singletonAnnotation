package com.example.demo.annotation.component.service.impl;

import com.example.demo.annotation.component.service.ComponentService;
import com.example.demo.annotation.component.service.dependencyInjectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DependencyInjectionServiceImpl implements dependencyInjectionService {
    private final ComponentService componentService;
//
//    @Autowired
//    public DependencyInjectionServiceImpl(ComponentService componentService) {
//        this.componentService = componentService;
//    }

    @Override
    public void injectTest() {
        System.out.println("DependencyInjectionServiceImpl 도착!");
        componentService.join("gbitkim");
    }
}
