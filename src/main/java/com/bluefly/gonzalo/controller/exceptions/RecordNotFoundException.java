package com.bluefly.gonzalo.controller.exceptions;

import java.text.MessageFormat;
import java.util.ResourceBundle;


public class RecordNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	ResourceBundle labels = ResourceBundle.getBundle("messages");

	public RecordNotFoundException(String recordKey) {
		super(recordKey);
	}
	
    public String getLocalizedMessage() {
    	return MessageFormat.format(labels.getString("record.key.not.found"), getMessage());
    }
}
