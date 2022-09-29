package com.stefanini.spotify.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    }
    //autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    }
    //configuração de recursos estaticos js, css, imagens etc
    @Override
    public void configure(WebSecurity web) throws Exception {

    }
}
