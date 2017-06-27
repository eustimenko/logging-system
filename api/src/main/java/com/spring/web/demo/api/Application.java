package com.spring.web.demo.api;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.*;
import com.spring.web.demo.api.filter.AuthFilter;
import com.spring.web.demo.logic.LogicConfiguration;
import com.spring.web.demo.persistent.PersistentConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.Filter;
import java.text.SimpleDateFormat;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Import(
        {
                PersistentConfiguration.class,
                LogicConfiguration.class,
                SwaggerConfiguration.class,
                JacksonConfiguration.class
        }
)
public class Application {

    @Bean
    public Filter authFilter() {
        return new AuthFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
