package com.cook.talk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class RootConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		  registry.addResourceHandler("/css/**")
		  .addResourceLocations("classpath:/static/css/");
		  registry.addResourceHandler("/js/**")
		  .addResourceLocations("classpath:/static/js/");
		  registry.addResourceHandler("/icon/**")
		  .addResourceLocations("classpath:/static/icon/");
		  registry.addResourceHandler("/img/**")
		  .addResourceLocations("classpath:/static/img/");
			/*
			 * registry.addResourceHandler("/uploadImg/**")
			 * .addResourceLocations("file:C:/Users/Pictures/rcporder/");
			 */
		 
    }
}