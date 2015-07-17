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

import com.bluefly.gonzalo.model.Reminder;
import com.bluefly.gonzalo.service.ReminderService;

@RestController
@RequestMapping(value = "/tps")
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

	/*
	 * This method will list all existing reminders.
	 */
	/*
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listReminders(ModelMap model) {

		List<Reminder> reminders = service.findAllReminders();
		model.addAttribute("reminders", reminders);
		return "allemployees";
	}
	*/

//	/*
//	 * This method will provide the medium to add a new employee.
//	 */
//	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
//	public String newReminder(ModelMap model) {
//		Reminder employee = new Reminder();
//		model.addAttribute("employee", employee);
//		model.addAttribute("edit", false);
//		return "registration";
//	}

    @RequestMapping(value = "/create",   method = RequestMethod.POST)
	public Reminder  create(@RequestBody Reminder reminder) {
        if (reminder.getHoursUntil()== null){
        	throw new BadRequestException("missing hours");
        }else if (reminder.getUrl() == null){
        	throw new BadRequestException("missing url");
        }else if (reminder.getText()==null){
        	throw new BadRequestException("missing text");
        }
		Reminder result = service.saveReminder(reminder);
		return result;
	}
    
    @RequestMapping(value = "/updateText",  method = RequestMethod.PUT)
	public Reminder  updateText(@RequestParam int id, @RequestParam String newText) {
    	Reminder reminder = service.findById(id);
        if ( reminder == null){
        	throw new  RecordNotFoundException("record id=" + id);
        }
        reminder.setText(newText);
        Reminder result = service.updateReminder(reminder);
		return result;
	}   
    
    @RequestMapping(value = "/updateUrl",  method = RequestMethod.PUT)
	public Reminder  updateUrl(@RequestParam int id, @RequestParam String newUrl) {
    	Reminder reminder = service.findById(id);
        if ( reminder == null){
        	throw new  RecordNotFoundException("record id=" + id);
        }
        reminder.setUrl(newUrl);
        Reminder result = service.updateReminder(reminder);
		return result;
	} 
    
    @RequestMapping(value = "/updateHoursUntil",  method = RequestMethod.PUT)
	public Reminder  updateHoursUntill(@RequestParam int id, @RequestParam int newHoursUntil) {
    	Reminder reminder = service.findById(id);
        if ( reminder == null){
        	throw new  RecordNotFoundException("record id=" + id);
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
        	throw new  RecordNotFoundException("record id=" + id);
        }
        service.delete(reminder);
	} 
    
    


	/*
	 * This method will provide the medium to update an existing employee.
	 */
//	@RequestMapping(value = { "/edit-{ssn}-employee" }, method = RequestMethod.GET)
//	public String editReminder(@PathVariable String ssn, ModelMap model) {
//		Reminder employee = service.findReminderBySsn(ssn);
//		model.addAttribute("employee", employee);
//		model.addAttribute("edit", true);
//		return "registration";
//	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
    /*
	@RequestMapping(value = { "/edit-{ssn}-employee" }, method = RequestMethod.POST)
	public String updateReminder(@Valid Reminder employee, BindingResult result,
			ModelMap model, @PathVariable String ssn) {

		if (result.hasErrors()) {
			return "registration";
		}

//		if(!service.isReminderSsnUnique(employee.getId(), employee.getText())){
//			FieldError ssnError =new FieldError("employee","ssn",messageSource.getMessage("non.unique.ssn", new String[]{employee.getText()}, Locale.getDefault()));
//		    result.addError(ssnError);
//			return "registration";
//		}

		service.updateReminder(employee);

		model.addAttribute("success", "Reminder " + employee.getUrl()	+ " updated successfully");
		return "success";
	}

	
//	/*
//	 * This method will delete an employee by it's SSN value.
//	 */
//	@RequestMapping(value = { "/delete-{ssn}-employee" }, method = RequestMethod.GET)
//	public String deleteReminder(@PathVariable String ssn) {
//		service.deleteReminderBySsn(ssn);
//		return "redirect:/list";
//	}

}
