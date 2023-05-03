package com.example.demosecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    public SecurityConfiguration(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public static BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests().antMatchers("/hello").permitAll()
                .and()
                .authorizeRequests().antMatchers("/admin").hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeRequests().antMatchers("/user").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .and()
                .authorizeRequests().anyRequest().authenticated();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select login, password, 'true' from server_user " +
                                "where login=?")
                .authoritiesByUsernameQuery(
                        "select login, authority from server_user " +
                                "where login=?");
    }
}
