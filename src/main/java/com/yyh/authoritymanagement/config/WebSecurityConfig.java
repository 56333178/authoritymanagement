package com.yyh.authoritymanagement.config;

import com.yyh.authoritymanagement.service.impl.EntryPointUnauthorizedHandler;
import com.yyh.authoritymanagement.service.impl.JwtAuthenticationTokenFilter;
import com.yyh.authoritymanagement.service.impl.RestAccessDeniedHandler;
import com.yyh.authoritymanagement.service.impl.SecurityInterceptorImpl;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AccessDecisionManager accessDecisionManager;
    @Autowired
    private SecurityInterceptorImpl securityInterceptor;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;
    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers(
                /*"/js/**",
                "/css/**",
                "/img/**",
                "/login/**","/user/register","/yzm","/hello"*/

                "/user/**").permitAll()
                .anyRequest().authenticated().accessDecisionManager(accessDecisionManager)
                .and().formLogin().loginProcessingUrl("/login").successForwardUrl("/hello")/*.failureForwardUrl("/login")*/.permitAll().and()
                .cors().and().rememberMe().rememberMeCookieName("workspace").rememberMeParameter("remember-me").and().headers().cacheControl();;
        httpSecurity.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.exceptionHandling().authenticationEntryPoint(entryPointUnauthorizedHandler).accessDeniedHandler(restAccessDeniedHandler);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
         .passwordEncoder(passwordEncoder());
    }

    // /*** 密码加密*/
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
       /* auth
                .inMemoryAuthentication()
                .withUser("yyh").password("123456").roles("USER");*/
    }



}
