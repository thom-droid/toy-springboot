package org.cannedcoffee.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.cannedcoffee.springboot.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests() // authority check per url
                .antMatchers("/", "/css/**","/image/**","/js/**","/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .logout() // logout url
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()// oauth config
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);

    }
}
