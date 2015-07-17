package com.bluefly.gonzalo.dao;

import java.util.List;

import com.bluefly.gonzalo.model.Reminder;

public interface ReminderDao {

	Reminder findById(int id);

	Reminder saveReminder(Reminder employee);
	
	long count();
	
	void delete(Reminder reminder);
	
	List<Reminder> findAllReminders(long start, long count);
	
	public List<Reminder> readNotConsumed(int size);


}
