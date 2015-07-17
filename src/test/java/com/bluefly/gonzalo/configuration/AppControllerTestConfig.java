package com.bluefly.gonzalo.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.bluefly.gonzalo.service.ReminderService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

@Configuration
/**
 * Mock service for tests
 * @author gdiaz
 *
 */
public class AppControllerTestConfig {

	 
	  @Bean
	  @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	  public ReminderService mockPersonService() {
	    return Mockito.mock(ReminderService.class);
	  }
	 
	}