package com.bluefly.gonzalo.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.bluefly.gonzalo")
@EnableScheduling
/**
 * main configuration class
 * @author gdiaz
 *
 */
public class AppConfig {
	

	
	@Bean
	public MessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages");
	    return messageSource;
	}
	
	/**
	 * this allows for replacements of "${xxx}" expressions on values and annotations
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertPlaceHolderConfiguer() {
	  return new PropertySourcesPlaceholderConfigurer();
	}
}

