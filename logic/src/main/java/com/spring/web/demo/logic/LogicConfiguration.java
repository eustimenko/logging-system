package com.spring.web.demo.logic;

import com.spring.web.demo.logic.service.*;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan("com.spring.web.demo.logic.service")
public class LogicConfiguration {

    @Bean
    public ProductService productService() {
        return new DefaultProductService();
    }

    @Bean
    public UserService userService() {
        return new DefaultUserService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
