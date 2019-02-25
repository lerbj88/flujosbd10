package com.flujos.flujosbd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class KondigurasiSecurity extends WebSecurityConfigurerAdapter{
    public static final String SQL_LOGIN
            //="select username, password, active as enabled \n" +
            ="select usuario, password, active\n" +
            "from usuarios where usuario = ?";

    public static final String SQL_PERMISSION
            = "select u.usuario, r.role as authority\n" +
            "from usuarios u join usuarios_roles ur on u.id = ur.id_usuario\n" +
            "join roles r on ur.id_role = r.id\n" +
            "where u.usuario = ?";

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configurGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth

                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(SQL_LOGIN)
                .authoritiesByUsernameQuery(SQL_PERMISSION)
                .passwordEncoder(passwordEncoder()); // bcrypt


    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()


                .antMatchers("/static/js/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/fonts/**").permitAll()


                .anyRequest().authenticated()
                .and()
                .formLogin()
               .loginPage("/login").permitAll()
                .defaultSuccessUrl("/usuarios/list", true)
                .and()
                .logout();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
