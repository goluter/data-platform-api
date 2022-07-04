package com.govey.config;

import com.govey.service.UserService;
import com.govey.web.AuthFailureHandler;
import com.govey.web.AuthSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final AuthSuccessHandler authSuccessHandler;
    private final AuthFailureHandler authFailureHandler;

    @Bean
    public BCryptPasswordEncoder encryptPassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(encryptPassword());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/", "/login").permitAll()
                .anyRequest().permitAll().and()
                .formLogin().loginPage("/login").loginProcessingUrl("/login/action")
                .successHandler(authSuccessHandler).failureHandler(authFailureHandler).and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me").permitAll().and()
                .sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true)
                .expiredUrl("/login?error=true&exception=sesssion_expired").and()
                .and().rememberMe().alwaysRemember(false)
                .tokenValiditySeconds(43200)
                .rememberMeParameter("remember-me");
    }
}
