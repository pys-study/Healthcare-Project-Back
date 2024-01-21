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

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")                    // GET, POST, PUT 등 모든 메서드 허용
//                        .allowedOriginPatterns("*") // ec2 web-server uri 추가할 것 // front domain 추가
//                        .allowedOrigins("http://localhost:3000")
                        .allowedOrigins("https://care.healthcare-hwp.com/")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

}
