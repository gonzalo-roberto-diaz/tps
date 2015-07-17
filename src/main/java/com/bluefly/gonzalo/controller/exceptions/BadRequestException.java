package com.bluefly.gonzalo.controller.exceptions;

import java.util.ResourceBundle;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * registered exception for some of the input validations done at the controller
 * @author gdiaz
 *
 */
public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	ResourceBundle labels = ResourceBundle.getBundle("messages");

	public BadRequestException(String messageKey) {
		super(messageKey);
	}
	
    public String getLocalizedMessage() {
        return labels.getString(getMessage());
    }
}
