package com.vinodh.config;

import org.springframework.beans.factory.annotation.Value;
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
public class SwaggerConfig {

	@Value("${spring.application.name}")
	private String serviceName;

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.vinodh")).build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfoBuilder apiInfoBuilder=new ApiInfoBuilder();
		apiInfoBuilder.title(""+serviceName.toUpperCase()+ " Microservice");
		apiInfoBuilder.contact(new Contact("Velankani Software Private Limited", "https://vcti.io/", "vinodh.sangavaram@velankani.com"));
		apiInfoBuilder.description(serviceName.toUpperCase()+ " Rest Storing all alarms");
		apiInfoBuilder.license("License of API");
		apiInfoBuilder.licenseUrl("https://vcti.io/");
		apiInfoBuilder.version("7.0.0");
		return apiInfoBuilder.build();
	}

}
