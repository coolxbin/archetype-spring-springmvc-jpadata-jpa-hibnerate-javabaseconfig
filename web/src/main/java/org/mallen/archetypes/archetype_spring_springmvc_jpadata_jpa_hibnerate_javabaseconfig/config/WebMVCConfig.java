package org.mallen.archetypes.archetype_spring_springmvc_jpadata_jpa_hibnerate_javabaseconfig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "org.mallen.archetypes.archetype_spring_springmvc_jpadata_jpa_hibnerate_javabaseconfig.web" }, includeFilters = { @Filter(type = FilterType.ANNOTATION, value = { Controller.class }) })
@Import(value = { ApplicationContextConfig.class })
public class WebMVCConfig extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver internalResolver() {
		InternalResourceViewResolver internalResolver = new InternalResourceViewResolver();
		internalResolver.setPrefix("/WEB-INF/views/");
		internalResolver.setSuffix(".jsp");
		return internalResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
	}
}
