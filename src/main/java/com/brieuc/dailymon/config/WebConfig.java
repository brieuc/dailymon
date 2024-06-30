package com.brieuc.dailymon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.brieuc.dailymon.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {  
    

    private final UserRepository userRepository;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
        .addResourceLocations("classpath:/static/","classpath:/images/")
        .setCachePeriod(0);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
        .allowCredentials(false)
        .allowedHeaders("*")
        .allowedMethods("*")
        .allowedOrigins("*");
    }


 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

     @Bean
    public UserDetailsService userDetailsService() {
        /*
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
            }
        };
        //return  username -> userRepository.findByUsername(username).orElseThrow();
        */
        return (String username) -> {
            return userRepository.findByUsername(username).orElseThrow();
        };
    }
}