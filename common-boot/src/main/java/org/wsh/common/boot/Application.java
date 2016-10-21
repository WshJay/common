package org.wsh.common.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  Spring boot初体验
 * since Date： 2016-10-21 14:02
 */
@RestController
@EnableAutoConfiguration
public class Application {

    @RequestMapping("/")
    public String home() {
        return "Hello Spring boot";
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.addListeners(new ApplicationStartedEventListener());
        SpringApplication.run(Application.class, args);
    }
}
