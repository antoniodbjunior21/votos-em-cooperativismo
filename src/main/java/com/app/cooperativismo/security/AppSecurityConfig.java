package com.app.cooperativismo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.app.cooperativismo.security.SecurityConstants.SIGN_UP_URL;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().and().authorizeRequests().anyRequest().permitAll();
        //http.cors().and().authorizeRequests()
          //      .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
            //    .anyRequest().authenticated()
              //  .and()
                //.addFilter(jwtAuthenticationFilter)
                //.addFilter(jwtAuthorizationFilter)
                // this disables session creation on Spring Security
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                /*
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowCredentials(true).maxAge(3600);*/
               //registry.addMapping("/greeting").allowedOrigins("http://localhost:3000");
              // registry.addMapping("/greeting").allowedOrigins("http://localhost:8080");
            }
        };
    }
}
