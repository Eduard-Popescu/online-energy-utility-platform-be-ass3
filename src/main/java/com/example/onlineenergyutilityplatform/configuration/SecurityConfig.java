package com.example.onlineenergyutilityplatform.configuration;

import com.example.onlineenergyutilityplatform.security.TokenValidatorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final TokenValidatorFilter tokenValidatorFilter;

  @Autowired
  public SecurityConfig(TokenValidatorFilter tokenValidatorFilter) {
    this.tokenValidatorFilter = tokenValidatorFilter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors()
//        .configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
        .and()
        .authorizeRequests()
        .antMatchers("/login")
        .permitAll()
        .antMatchers("/ws/**")
        .permitAll()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .csrf()
        .disable()
        .addFilterBefore(tokenValidatorFilter, UsernamePasswordAuthenticationFilter.class)
        .httpBasic();

    http.headers().frameOptions().sameOrigin();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
