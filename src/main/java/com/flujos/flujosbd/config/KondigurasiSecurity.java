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
            ="select fiusuario, fcpassword, fiactive\n" +
            "from usuarios where fiusuario = ?";

    public static final String SQL_PERMISSION
            = "select u.fiusuario, r.fcrol as authority\n" +
            "from usuarios u join usuarios_roles ur on u.fiid = ur.fiidusuario\n" +
            "join roles r on ur.fiidrole = r.fiid\n" +
            "where u.fiusuario = ?";

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
           //     .csrf().disable()
                .formLogin()

               .loginPage("/login").permitAll()
                .defaultSuccessUrl("/usuarios/list", true)

                .and()
               // .csrf().disable()
                .logout();
            }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
