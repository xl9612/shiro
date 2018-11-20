package com.define.yt.yitian2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.definesys.mpaas", "com.define.yt.yitian2"})
public class Yitian2Application {
    public static void main(String[] args) {
        SpringApplication.run(Yitian2Application.class, args);
    }
}
