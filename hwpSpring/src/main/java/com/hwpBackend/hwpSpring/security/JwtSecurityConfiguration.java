package com.hwpBackend.hwpSpring.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.UUID;

import static com.nimbusds.jose.jwk.RSAKey.*;

@Configuration
@EnableWebSecurity
public class JwtSecurityConfiguration {

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
            auth.anyRequest().authenticated(); // 모든 요청 접근 허용
        });
        http.sessionManagement( // 세선 해제
                session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS) // 세션 생성 정책 설정, 사용과 설정 둘 다 안하도록
        );
        http.httpBasic(Customizer.withDefaults());

        http.csrf(AbstractHttpConfigurer::disable); // csrf 사용 해제

        http.headers(headers -> headers.frameOptions(
                HeadersConfigurer.FrameOptionsConfig::sameOrigin)); // 요청이 동일한 오리진에서 오는 경우 해당 애플리케이션에 프레임 허용(h2 콘솔 사용하기 위함)


//        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt); // oauth2 리소스 서버 사용 변경 전 코드
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())); // oauth2 리소스 서버 사용


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

    // 로컬 mariaDB
//    @Bean
//    public DataSource dataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
//        dataSource.setUrl("jdbc:mariadb://localhost:3300/hwpDatabase");
//        dataSource.setUsername("root");
//        dataSource.setPassword("1234");
//
//        return dataSource;
//    }

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

    @Bean
    public KeyPair keyPair() throws NoSuchAlgorithmException { // 키페어 생성
        var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // 키 사이즈가 커질수록 보안성 높아짐, 2048비트 RSA 암호화
        return keyPairGenerator.generateKeyPair();
    }

    @Bean
    public RSAKey rsaKey(KeyPair keyPair) {             // RSA 키 생성 import com.nimbusds.jose.jwt.RSAKey
        return new
                Builder((RSAPublicKey) keyPair.getPublic()) // 공개 키 설정
                .privateKey(keyPair.getPrivate())       // 비밀 키 설정
                .keyID(UUID.randomUUID().toString())    // 키 ID 생성
                .build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey){ // 키 세트의 리소스 생성
        var jwkSet = new JWKSet(rsaKey);

//        var jwkSource = new JWKSource(){ // get() 메소드에 구현물 제공
//            @Override
//            public List<JWK> get(JWKSelector jwkSelector, SecurityContext securityContext) throws KeySourceException {
//                return jwkSelector.select(jwkSet);
//            }
//        };

        // 위의 모든 소스를 제거하고 간단하게 람다 함수로 표현
        return (jwkSelector, context) -> jwkSelector.select(jwkSet);
    }

    @Bean
    public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException { // 디코더 생성
        return NimbusJwtDecoder
                .withPublicKey(rsaKey.toRSAPublicKey())
                .build();
    }

    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource){ // 인코더 생성
        return new NimbusJwtEncoder(jwkSource);
    }
}
