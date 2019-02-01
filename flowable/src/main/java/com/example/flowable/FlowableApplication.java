package com.example.flowable;

import org.flowable.idm.api.IdmIdentityService;
import org.flowable.idm.api.Privilege;
import org.flowable.idm.api.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class FlowableApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableApplication.class, args);
    }


    /**
     * 初始化用户
     */
    @Component
    public class UserCreatorCommandLineRunner implements CommandLineRunner {

        protected final IdmIdentityService idmIdentityService;

        public UserCreatorCommandLineRunner(IdmIdentityService idmIdentityService) {
            this.idmIdentityService = idmIdentityService;
        }

        @Override
        public void run(String... args) {
            createUserIfNotExists("flowfest");
            createUserIfNotExists("flowfest-actuator");
            createUserIfNotExists("flowfest-rest");

            if (idmIdentityService.createPrivilegeQuery().privilegeName("ROLE_REST").count() == 0) {
                Privilege restPrivilege = idmIdentityService.createPrivilege("ROLE_REST");
                idmIdentityService.addUserPrivilegeMapping(restPrivilege.getId(), "flowfest-rest");
            }

            if (idmIdentityService.createPrivilegeQuery().privilegeName("ROLE_ACTUATOR").count() == 0) {
                Privilege restPrivilege = idmIdentityService.createPrivilege("ROLE_ACTUATOR");
                idmIdentityService.addUserPrivilegeMapping(restPrivilege.getId(), "flowfest-actuator");
            }
        }

        protected void createUserIfNotExists(String username) {
            if (idmIdentityService.createUserQuery().userId(username).count() == 0) {
                User user = idmIdentityService.newUser(username);
                user.setPassword("test");
                idmIdentityService.saveUser(user);
            }
        }
    }

//    @Configuration
   /* public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            HttpBasicConfigurer<HttpSecurity> httpSecurityHttpBasicConfigurer = http
                    .authorizeRequests()
                    .requestMatchers(EndpointRequest.to(InfoEndpoint.class, HealthEndpoint.class)).permitAll()
                    .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ACTUATOR")
                    .antMatchers("/*-api/**").hasRole("REST")
                    .antMatchers("/h2-console/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
                    .csrf().disable()
                    .httpBasic();
        }
    }*/
}

