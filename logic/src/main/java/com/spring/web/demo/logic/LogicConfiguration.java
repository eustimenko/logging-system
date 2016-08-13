package com.spring.web.demo.logic;

import com.spring.web.demo.logic.service.*;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.spring.web.demo.logic.service")
public class LogicConfiguration {

    @Bean
    public ProductService productService() {
        return new DefaultProductService();
    }
}
