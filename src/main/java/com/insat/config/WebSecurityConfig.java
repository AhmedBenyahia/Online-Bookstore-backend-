package com.insat.config;


import org.omg.CORBA.Request;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.http.HttpMethod.*;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers(POST, "/**");
        webSecurity.ignoring().antMatchers(GET, "/**");
        webSecurity.ignoring().antMatchers(OPTIONS, "/**");
        webSecurity.ignoring().antMatchers(PUT, "/**");
        webSecurity.ignoring().antMatchers(DELETE, "/**");
    }
}