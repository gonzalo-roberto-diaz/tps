package com.bluefly.gonzalo.controller.exceptions;

import java.text.MessageFormat;
import java.util.ResourceBundle;


/**
 * registered exception for DELETE and PUT - style calls, to be thrown when a record is not found.
 * @author gdiaz
 *
 */
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
