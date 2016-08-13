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
import org.springframework.context.annotation.*;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.Filter;
import java.text.SimpleDateFormat;

@SpringBootApplication
@Import({PersistentConfiguration.class, LogicConfiguration.class})
@EnableSwagger
public class Application {

    @Bean
    public View jsonTemplate() {
        final MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setPrettyPrint(true);
        return view;
    }

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.indentOutput(true);
        builder.dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        return builder;
    }

    @Bean
    public Filter authFilter() {
        return new AuthFilter();
    }

    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(new ApiInfo(
                        "StoreLog rest API",
                        "This app is for education, training purpose. It represents model of wide product storage.",
                        null,
                        null,
                        null,
                        null
                ))
                .useDefaultResponseMessages(false)
                .includePatterns("/api.*");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
