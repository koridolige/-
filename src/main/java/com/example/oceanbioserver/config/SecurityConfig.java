package com.example.oceanbioserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Spring Security配置类
 * 实现两种安全配置：开发环境和生产环境
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * 开发环境安全配置 - 放行所有测试需要的接口
     */
    @Bean
    @Profile("dev")
    public SecurityFilterChain filterChainDev(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                // 第一个项目的放行路径
                .antMatchers("/WxUser/login", "/WxUser/register", "/WxUser/getToken","/banner/findBanner","/WxUser/checkTokenStatus","/WxUser/findUserInfo").permitAll()
                // 第二个项目的放行路径
                .antMatchers("/user/login", "/user/register", "/user/getUserInfo").permitAll()
                // 商品相关接口
                .antMatchers("/goods/**").permitAll()
                // 静态资源和文件
                .antMatchers("/images/**", "/file/**", "/static/**").permitAll()
                // 其他开发环境需要的放行路径
                .antMatchers("/category/**", "/ebook/**", "/doc/**").permitAll()
                .antMatchers("/login", "/login.html", "/error.html", "/toError").permitAll()
                // 其他请求需要认证
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * 生产环境安全配置 - 只放行必要的接口
     */
    @Bean
    @Profile("!dev")
    public SecurityFilterChain filterChainProd(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                // 第一个项目的生产环境放行路径
                .antMatchers("/WxUser/login", "/WxUser/register","/banner/findBanner","/WxUser/checkTokenStatus","/WxUser/findUserInfo").permitAll()
                // 第二个项目的生产环境放行路径
                .antMatchers("/user/login", "/user/register").permitAll()
                // 生产环境必要的静态资源
                .antMatchers("/images/**", "/file/**").permitAll()
                // 其他请求需要认证
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * 配置忽略的路径（不走安全过滤器）
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers(
                "/favicon.ico",
                "/error",
                "/static/**",
                "/images/**",
                "/file/**",
                // 第一个项目需要忽略的路径
                "/WxUser/login",
                "/WxUser/register",
                "/WxUser/getToken",
                // 第二个项目需要忽略的路径
                "/login",
                "/user/getUserInfo",
                // 商品相关接口
                "/goods/**",
                // 其他需要忽略的路径
                "/login.html",
                "/error.html",
                "/toError"
        );
    }

    /**
     * 跨域配置
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}