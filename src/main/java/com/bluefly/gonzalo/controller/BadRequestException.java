package com.bluefly.gonzalo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BadRequestException(String reason) {
		super(reason);
	}
}
