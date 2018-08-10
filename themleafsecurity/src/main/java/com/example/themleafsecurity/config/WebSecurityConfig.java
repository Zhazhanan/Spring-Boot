package com.example.themleafsecurity.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.themleafsecurity.security.HuhaPasswordEncoder;
import com.example.themleafsecurity.security.service.DbUserDetailService;
import com.example.themleafsecurity.security.service.HuhaAuthenticationProviderDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author WangKun
 * @create 2018-08-09
 * @desc
 **/
@EnableWebSecurity
@Import(DataSourceConfig.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    @Autowired
    private DruidDataSource druidDataSource;

    @Bean
    public HuhaPasswordEncoder huhaPasswordEncoder() {
        return new HuhaPasswordEncoder();
    }

    @Bean
    public DbUserDetailService dbUserDetailService() {
        DbUserDetailService dbUserDetailService = new DbUserDetailService();
        dbUserDetailService.setDataSource(druidDataSource);
        dbUserDetailService.setAuthoritiesByUsernameQuery("SELECT login_name username, 'admin' AS authority FROM dashboard_user WHERE login_name = ?");
        dbUserDetailService.setUsersByUsernameQuery("SELECT user_id,user_name,login_name, user_password, 1 AS enabled FROM dashboard_user WHERE login_name = ? ");
        return dbUserDetailService;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(dbUserDetailService());
        daoAuthenticationProvider.setPasswordEncoder(huhaPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public HuhaAuthenticationProviderDecorator huhaAuthenticationProviderDecorator() {
        HuhaAuthenticationProviderDecorator share = new HuhaAuthenticationProviderDecorator();
        share.setAuthenticationProvider(daoAuthenticationProvider());
        return share;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(huhaAuthenticationProviderDecorator());
//        auth.inMemoryAuthentication().passwordEncoder(new HuhaPasswordEncoder())
//                .withUser("user").password("1").roles("USER")
//                .and()
//                .withUser("admin").password("1").roles("ADMIN");
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/plugins/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home", "/about").permitAll()
                .antMatchers("/user/**").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }
}
