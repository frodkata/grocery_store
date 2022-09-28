package com.Exercise.GroceryStore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //Ensure that a normal String can be passed as password instead of BCrypt
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    //Configure in memory Authentication for Spring Security
    //[Define user credentials and roles]
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user")
                .password("1234")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("1234")
                .roles("ADMIN");
    }

    //Configure API endpoints and secured them based on roles
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/helloAdmin").hasRole("ADMIN")
                .antMatchers("/helloUser").hasAnyRole("USER","ADMIN")
                .and()
                .formLogin();
    }
}
