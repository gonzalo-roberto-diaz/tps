package com.bluefly.gonzalo.service;

import java.util.List;

import com.bluefly.gonzalo.model.Reminder;

public interface ReminderService {

	Reminder findById(int id);
	
	Reminder saveReminder(Reminder reminder);
	
	Reminder updateReminder(Reminder reminder);
	
	void delete(Reminder reminder);

	List<Reminder> findAllReminders(long start, long size);
	
	long count();
	
	public void calculatePostingTime(Reminder reminder);
	
	public void postDues();
	

	
}
