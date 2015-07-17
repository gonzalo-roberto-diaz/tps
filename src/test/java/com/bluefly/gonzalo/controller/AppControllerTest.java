package com.bluefly.gonzalo.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.AnyOf;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.atLeastOnce;

import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;







import com.bluefly.gonzalo.model.Reminder;
import com.bluefly.gonzalo.service.ReminderService;

public class AppControllerTest {

	@Mock
	ReminderService service;
	
	@Mock
	MessageSource message;
	
	AppController ctrl;
	
	@Spy
	List<Reminder> reminders = new ArrayList<>();

	@Spy
	ModelMap model;
	
	@Mock
	BindingResult result;
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		reminders = getReminderList();
		ctrl = Mockito.mock(AppController.class);
	}
	/*
	@Test
	public void listReminders(){
		when(service.findAllReminders()).thenReturn(reminders);
		Assert.assertEquals(appController.listReminders(model), "allemployees");
		Assert.assertEquals(model.get("employees"), reminders);
		verify(service, atLeastOnce()).findAllReminders();
	}
	*/
	
	/*
	@Test
	public void newReminder(){
		Assert.assertEquals(appController.newReminder(model), "registration");
		Assert.assertNotNull(model.get("employee"));
		Assert.assertFalse((Boolean)model.get("edit"));
		Assert.assertEquals(((Reminder)model.get("employee")).getId(), 0);
	}
	*/

/*
	@Test
	public void saveReminderWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).saveReminder(any(Reminder.class));
		Assert.assertEquals(appController.saveReminder(reminders.get(0), result, model), "registration");
	}
	*/

	/*
	@Test
	public void saveReminderWithValidationErrorNonUniqueSSN(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isReminderSsnUnique(anyInt(), anyString())).thenReturn(false);
		Assert.assertEquals(appController.saveReminder(reminders.get(0), result, model), "registration");
	}
	*/

	
	@Test
	public void create(){
		Reminder emp = reminders.get(0);
		when(ctrl.create(emp)).thenReturn(emp);
		Assert.assertNotNull(ctrl.create(emp));
		Assert.assertEquals(ctrl.create(emp), emp);
	}
	
	@Test
	public void updateText(){
		Reminder rem = new Reminder();
		rem.setId(1);
		rem.setText("a text");
		String newText = "newText";
		Reminder rem2 = new Reminder();
		rem2.setId(1);
		rem2.setText(newText);
		
		when(ctrl.updateText(rem.getId(), newText)).thenReturn(rem2);
		Assert.assertNotNull(ctrl.updateText(rem.getId(), newText));
		Assert.assertNotEquals(ctrl.updateText(rem.getId(), newText).getText(), rem.getText());
		Assert.assertEquals(ctrl.updateText(rem.getId(), newText), rem2);
	}	
	
	@Test
	public void updateHoursUntil(){
		DateTime now = new DateTime();
		Reminder rem = new Reminder();
		rem.setId(1);
		rem.setHoursUntil(2);
		rem.setPostingTime(now.minusDays(10).toDate());
		int newHours = 10;
		Reminder rem2 = new Reminder();
		rem2.setId(1);
		rem2.setHoursUntil(newHours);
		rem2.setPostingTime(now.plusHours(newHours).toDate());
		
		when(ctrl.updateHoursUntill(rem.getId(), newHours)).thenReturn(rem2);
		Assert.assertNotNull(ctrl.updateHoursUntill(rem.getId(), newHours));
		Assert.assertNotEquals(ctrl.updateHoursUntill(rem.getId(), newHours).getHoursUntil(), rem.getHoursUntil());
		Assert.assertNotEquals(ctrl.updateHoursUntill(rem.getId(), newHours).getPostingTime(), rem.getPostingTime());
		Assert.assertEquals(ctrl.updateHoursUntill(rem.getId(), newHours), rem2);
	}		
	
	@Test
	public void updateUrl(){
		Reminder rem = new Reminder();
		rem.setId(1);
		rem.setUrl("a URL");
		String newUrl = "newUrl";
		Reminder rem2 = new Reminder();
		rem2.setId(1);
		rem2.setUrl(newUrl);
		
		when(ctrl.updateUrl(rem.getId(), newUrl)).thenReturn(rem2);
		Assert.assertNotNull(ctrl.updateUrl(rem.getId(), newUrl));
		Assert.assertNotEquals(ctrl.updateUrl(rem.getId(), newUrl).getUrl(), rem.getUrl());
		Assert.assertEquals(ctrl.updateUrl(rem.getId(), newUrl), rem2);
	}	
	
	@Test
	public void all(){
		when(ctrl.all(anyInt(), anyInt())).thenReturn(reminders);
		Assert.assertNotNull(ctrl.all(0, 3));
		Assert.assertEquals(ctrl.all(0,3).size(), reminders.size());
	}
	
	
/*
	@Test
	public void editReminder(){
		Reminder emp = reminders.get(0);
		when(service.findReminderBySsn(anyString())).thenReturn(emp);
		Assert.assertEquals(appController.editReminder(anyString(), model), "registration");
		Assert.assertNotNull(model.get("employee"));
		Assert.assertTrue((Boolean)model.get("edit"));
		Assert.assertEquals(((Reminder)model.get("employee")).getId(), 1);
	}
	*/

	/*
	@Test
	public void updateReminderWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).updateReminder(any(Reminder.class));
		Assert.assertEquals(appController.updateReminder(reminders.get(0), result, model,""), "registration");
	}

	@Test
	public void updateReminderWithValidationErrorNonUniqueSSN(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isReminderSsnUnique(anyInt(), anyString())).thenReturn(false);
		Assert.assertEquals(appController.updateReminder(reminders.get(0), result, model,""), "registration");
	}
	*/

	/*
	@Test
	public void updateReminderWithSuccess(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isReminderSsnUnique(anyInt(), anyString())).thenReturn(true);
		doNothing().when(service).updateReminder(any(Reminder.class));
		Assert.assertEquals(appController.updateReminder(reminders.get(0), result, model, ""), "success");
		Assert.assertEquals(model.get("success"), "Reminder Axel updated successfully");
	}
	*/
	

	@Test
	public void deleteReminder(){
		doNothing().when(ctrl).delete(anyInt());
	}


	public List<Reminder> getReminderList(){
		Reminder e1 = new Reminder();
		e1.setId(1);
		e1.setUrl("http://google.com");
		e1.setHoursUntil(10);
		e1.setText("any message");
	
		
		Reminder e2 = new Reminder();
		e2.setId(2);
		e2.setUrl("Jeremy");
		e2.setHoursUntil(3);
		e2.setText("don't forget the memo");

		
		reminders.add(e1);
		reminders.add(e2);
		return reminders;
	}
}
