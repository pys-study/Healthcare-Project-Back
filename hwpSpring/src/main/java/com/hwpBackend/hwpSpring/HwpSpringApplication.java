package com.hwpBackend.hwpSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class HwpSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(HwpSpringApplication.class, args);
    }

    // Allow all request only from 3000 to 8000

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")                    // GET, POST, PUT 등 모든 메서드 허용
                        .allowedOrigins("*"); // ec2 web-server uri 추가할 것
            }
        };
    }
}
