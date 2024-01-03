package com.hwpBackend.hwpSpring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

//@Configuration
@EnableWebSecurity
public class BasicAuthSecurityConfiguration {

    // CORS 설정
    // Allow all request only from 3000 to 8000

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")                    // GET, POST, PUT 등 모든 메서드 허용
                        .allowedOrigins("*");                   // ec2 web-server uri 추가할 것 // front domain 추가
            }
        };
    }


    // CSRF 설정

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth
                    .requestMatchers("/users").hasRole("USER")
                    .requestMatchers("/admin/**").hasRole("ADMIN") // 매쳐 사용해서 인증 규칙 설정
                    .anyRequest().authenticated(); // 모든 요청 접근 허용
        });
        http.sessionManagement( // 세선 해제
                session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS) // 세션 생성 정책 설정, 사용과 설정 둘 다 안하도록
        );
        http.httpBasic(Customizer.withDefaults());

        http.csrf(AbstractHttpConfigurer::disable); // csrf 사용 해제

        http.headers(headers -> headers.frameOptions(
                HeadersConfigurer.FrameOptionsConfig::sameOrigin)); // 요청이 동일한 오리진에서 오는 경우 해당 애플리케이션에 프레임 허용(h2 콘솔 사용하기 위함)

        return http.build();
    }


    // 테스트용 H2 DB data source
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }

    // RDS mariaDB data source
//    @Bean
//    public DataSource dataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
//        dataSource.setUrl("jdbc:mariadb://hwp-database-3.csaeggt9fqg5.ap-northeast-2.rds.amazonaws.com:3306/hwpDatabase");
//        dataSource.setUsername("admin");
//        dataSource.setPassword("30rlrkq12!");
//
//        return dataSource;
//    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {  // db에 사용자 증명 저장

        // 사용자 설정
        var user = User.withUsername("user1")
                .password("dummy")
                .passwordEncoder(str -> passwordEncoder().encode(str)) // 입력값을 가져와 passwordEncoder를 사용해 인코딩
                .roles("USER")          // 역할 할당
                .build();


        var admin = User.withUsername("admin")
                .password("dummy")
                .passwordEncoder(str -> passwordEncoder().encode(str)) // 입력값을 가져와 passwordEncoder를 사용해 인코딩
                .roles("ADMIN", "USER")          // 역할 할당
                .build();

        var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.createUser(user);
        jdbcUserDetailsManager.createUser(admin);

        return jdbcUserDetailsManager;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { // 해싱
        return new BCryptPasswordEncoder();
    }
}
