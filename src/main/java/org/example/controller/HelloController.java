package org.example.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class HelloController {
    @Resource
    private HelloService helloService;
    @GetMapping("/hello")
    public String sayHello() {
        String result = helloService.sayHello();
        log.info("Result: {}", result);// 打印日志
        return result;
    }
}
