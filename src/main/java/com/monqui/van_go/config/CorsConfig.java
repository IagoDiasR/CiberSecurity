package com.monqui.van_go.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

	@Bean(name = "customCorsConfig")
	public WebMvcConfigurer corsConfig() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						// .allowedOrigins("http://localhost:4442")
						.allowedOrigins("http://localhost:*")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH").allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}
}
