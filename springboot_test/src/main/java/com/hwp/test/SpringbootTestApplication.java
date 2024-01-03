package com.hwp.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude= SecurityAutoConfiguration.class)
public class SpringbootTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTestApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") //특정한 패턴에 대해 CORS 요청 처리, /**는 와일드카드
						.allowedMethods("*") //어떤 종류의 메소드를 허용하는가, *는 와일드카드
						.allowedOrigins("*"); //어디에서 오는 요청을 허용하는가, react 기본 주소 허용
			}
		};
	}
}
