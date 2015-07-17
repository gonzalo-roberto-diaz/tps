package com.bluefly.gonzalo.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

import org.joda.time.LocalDate;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bluefly.gonzalo.dao.ReminderDao;
import com.bluefly.gonzalo.model.Reminder;
import com.bluefly.gonzalo.service.ReminderServiceImpl;

public class ReminderServiceImplTest {

	@Mock
	ReminderDao dao;
	
	@InjectMocks
	ReminderServiceImpl reminderService;
	
	@Spy
	List<Reminder> reminders = new ArrayList<Reminder>();
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		reminders = getReminderList();
	}

	@Test
	public void findById(){
		Reminder emp = reminders.get(0);
		when(dao.findById(anyInt())).thenReturn(emp);
		Assert.assertEquals(reminderService.findById(emp.getId()),emp);
	}
	
	/*

	@Test
	public void saveReminder(){
		doNothing().when(dao).saveReminder(any(Reminder.class));
		reminderService.saveReminder(any(Reminder.class));
		verify(dao, atLeastOnce()).saveReminder(any(Reminder.class));
	}
	
	@Test
	public void updateReminder(){
		Reminder emp = reminders.get(0);
		when(dao.findById(anyInt())).thenReturn(emp);
		reminderService.updateReminder(emp);
		verify(dao, atLeastOnce()).findById(anyInt());
	}
	
	*/


	
	@Test
	public void findAllReminders(){
		when(dao.findAllReminders(anyInt(), anyInt())).thenReturn(reminders);
		Assert.assertEquals(reminderService.findAllReminders(0, 2), reminders);
	}
	



	
	
	public List<Reminder> getReminderList(){
		Reminder e1 = new Reminder();
		e1.setId(1);
		e1.setUrl("Axel");
		e1.setPostingTime(new Date());
		e1.setHoursUntil(1);
		e1.setText("XXX111");
		
		Reminder e2 = new Reminder();
		e2.setId(2);
		e2.setUrl("Jeremy");
		e2.setPostingTime(new Date());
		e2.setHoursUntil(8);
		e2.setText("XXX222");
		
		reminders.add(e1);
		reminders.add(e2);
		return reminders;
	}
	
}
