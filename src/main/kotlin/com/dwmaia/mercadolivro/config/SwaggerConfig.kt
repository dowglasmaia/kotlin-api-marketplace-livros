package com.dwmaia.mercadolivro.config

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun api(): Docket =  Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dwmaia.mercadolivro.controller"))
                .paths(PathSelectors.any())
                .build()

    private fun getApiInfo(): ApiInfo {
        val contact = Contact(
                "Dowglas Maia ",
                "https://www.linkedin.com/in/dowglasmaia/",
                "dowglasmaia-dev@outlook.com")
        return ApiInfoBuilder()
                .title("Api - Marketplace Books")
                .description("")
                .version("1.0.0")
                .contact(contact)
                .build()
    }
}