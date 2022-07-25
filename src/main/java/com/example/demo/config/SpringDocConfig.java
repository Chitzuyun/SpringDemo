package com.example.demo.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
	
    @Bean
    public GroupedOpenApi employeeApi() {
            return GroupedOpenApi.builder()
                    .group("Employee")
                    .pathsToMatch("/employee/**")
                    .build();
    }

    @Bean
    public GroupedOpenApi departmentApi() {
            return GroupedOpenApi.builder()
                    .group("Department")
                    .pathsToMatch("/department/**")
                    .build();
    }
}
