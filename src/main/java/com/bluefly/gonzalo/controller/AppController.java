package com.bluefly.gonzalo.controller;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;









import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeEditor;
import org.springframework.http.ResponseEntity;

import com.bluefly.gonzalo.controller.exceptions.BadRequestException;
import com.bluefly.gonzalo.controller.exceptions.ErrorDetail;
import com.bluefly.gonzalo.controller.exceptions.RecordNotFoundException;
import com.bluefly.gonzalo.model.Reminder;
import com.bluefly.gonzalo.service.ReminderService;

@RestController
@RequestMapping(value = "/tps")
/**
 * REST controller of the application
 * @author gdiaz
 *
 */
public class AppController {

	@Autowired
	ReminderService service;
	
	@Autowired
	MessageSource messageSource;
	
	
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDetail badRequest(HttpServletRequest request, Exception exception) {
	    ErrorDetail error = new ErrorDetail();
	    error.setStatus(HttpStatus.BAD_REQUEST.value());
	    error.setMessage(exception.getLocalizedMessage());
	    error.setUrl(request.getRequestURL().append("/create").toString());
	    return error;
	}
	
	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorDetail notFound(HttpServletRequest request, Exception exception) {
	    ErrorDetail error = new ErrorDetail();
	    error.setStatus(HttpStatus.NOT_FOUND.value());
	    error.setMessage(exception.getLocalizedMessage());
	    error.setUrl(request.getRequestURL().append("/update").toString());
	    return error;
	}	


    @RequestMapping(value = "/create",   method = RequestMethod.POST)
	public Reminder  create(@RequestBody Reminder reminder) {
        if (reminder.getHoursUntil()== null){
        	throw new BadRequestException("hours.missing");
        }else if (reminder.getUrl() == null){
        	throw new BadRequestException("url.missing");
        }else if (reminder.getText()==null){
        	throw new BadRequestException("text.missing");
        }
		Reminder result = service.saveReminder(reminder);
		return result;
	}
    
    @RequestMapping(value = "/updateText",  method = RequestMethod.PUT)
	public Reminder  updateText(@RequestParam int id, @RequestParam String newText) {
    	Reminder reminder = service.findById(id);
        if ( reminder == null){
        	throw new  RecordNotFoundException(Integer.toString(id));
        }
        reminder.setText(newText);
        Reminder result = service.updateReminder(reminder);
		return result;
	}   
    
    @RequestMapping(value = "/updateUrl",  method = RequestMethod.PUT)
	public Reminder  updateUrl(@RequestParam int id, @RequestParam String newUrl) {
    	Reminder reminder = service.findById(id);
        if ( reminder == null){
        	throw new  RecordNotFoundException(Integer.toString(id));
        }
        reminder.setUrl(newUrl);
        Reminder result = service.updateReminder(reminder);
		return result;
	} 
    
    @RequestMapping(value = "/updateHoursUntil",  method = RequestMethod.PUT)
	public Reminder  updateHoursUntill(@RequestParam int id, @RequestParam int newHoursUntil) {
    	Reminder reminder = service.findById(id);
        if ( reminder == null){
        	throw new  RecordNotFoundException(Integer.toString(id));
        }
        reminder.setHoursUntil(newHoursUntil);
        service.calculatePostingTime(reminder);
        Reminder result = service.updateReminder(reminder);
		return result;
	}    
    
    @RequestMapping(value = "/all",  method = RequestMethod.GET)
	public List<Reminder>  all(@RequestParam int start, @RequestParam int size) {
    	return service.findAllReminders(start, size);
	} 
    
    @RequestMapping(value = "/count",  method = RequestMethod.GET)
	public long  count() {
    	return service.count();
	} 
    
    @RequestMapping(value = "/delete",  method = RequestMethod.DELETE)
	public void  delete(@RequestParam int id) {
    	Reminder reminder = service.findById(id);
        if ( reminder == null){
        	throw new  RecordNotFoundException(Integer.toString(id));
        }
        service.delete(reminder);
	} 
    


}
