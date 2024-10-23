package com.estsoft.springproject.user.config;

import com.estsoft.springproject.user.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

// 스프링 시큐리티 설정
@Configuration
public class WebSecurityConfiguration {

    private final UserDetailService userDetailService;

    public WebSecurityConfiguration(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    // ignore
    // 특정 요청은 스프링 시큐리티 설정을 타지 않도록 ignore 처리
    @Bean
    public WebSecurityCustomizer ignore() {
        return webSecurity -> webSecurity.ignoring()
//                .requestMatchers(toH2Console()) //  /h2-console
                .requestMatchers("/static/**","/swagger-ui/**","/v3/api-docs/**","/swagger-ui.html");
    }


    // 특정 요청에 대한 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(

                        // 3) 인증, 인가 설정
                        custom -> custom.requestMatchers("/login", "/signup", "/user").permitAll()
//                                .requestMatchers("/articles/**").hasRole("ADMIN") // 이 요청이 들어왔을땐(requestMatchers) 이 역할을 가지고 있어야 함 (hasRole)
                                .anyRequest().permitAll()
                )
                // 현버전엔 람다표현식으로 씀(아래는 구버전)
//                .requestMatchers("/login", "/signup", "/user").permitAll()
//                .requestMatchers("/api/external").hasRole("admin")  // 인가
//                .anyRequest().authenticated()
//                .and()  // and를 기준으로 각각의 필터들이 등록됨

                //4) 폼 기반 로그인 설정
                .formLogin(custom -> custom.loginPage("/login")
                        .defaultSuccessUrl("/articles",true)) // 성공했을때 리디렉션되는 url
//                .loginPage("/login")
//                .defaultSuccessUrl("/articles")  // 로그인 성공했을 경우 리다이렉션 url
//                .and()

                // 5) 로그아웃 설정
                .logout(custom -> custom.logoutSuccessUrl("/login")
                        .invalidateHttpSession(true))
//                .logoutSuccessUrl("/login")
//                .invalidateHttpSession(true)
//                .and()

                // 6) csrf 비활성화
                .csrf(custom -> custom.disable())
//                .csrf().disable()

                .build();
    }

    // 7) 인증관리자 관련 설정
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) {
//        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailService)  // 8) 사용자 정보 서비스 설정
//                .passwordEncoder(bCryptPasswordEncoder)
//                .build();
//    }
    // 패스워드 암호화 (Password Encoder) 방식 빈 등록
    // 스프링 시큐리티에서 제공해주는 클래스 사용
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
