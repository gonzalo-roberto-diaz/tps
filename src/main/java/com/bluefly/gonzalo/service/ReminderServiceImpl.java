package com.bluefly.gonzalo.service;

import java.net.URI;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import com.bluefly.gonzalo.dao.ReminderDao;
import com.bluefly.gonzalo.model.Reminder;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("reminderService")
@Transactional
public class ReminderServiceImpl implements ReminderService {
	
	Logger log = Logger.getLogger(ReminderServiceImpl.class.getName());

	@Autowired
	private ReminderDao dao;

	public Reminder findById(int id) {
		return dao.findById(id);
	}

	public Reminder saveReminder(@Validated Reminder reminder) {
		if (reminder != null) {
			calculatePostingTime(reminder);
		}
		return dao.saveReminder(reminder);
	}

	public Reminder updateReminder(Reminder reminder) {
		Reminder entity = dao.findById(reminder.getId());
		if (entity != null) {
			entity.setUrl(reminder.getUrl());
			entity.setPostingTime(reminder.getPostingTime());
			entity.setHoursUntil(reminder.getHoursUntil());
			entity.setText(reminder.getText());
		}
		return entity;
	}

	public void delete(Reminder reminder) {
		dao.delete(reminder);
	}

	public List<Reminder> findAllReminders(long start, long size) {
		return dao.findAllReminders(start, size);
	}

	public long count() {
		return dao.count();
	}

	/**
	 * calculates the posting time based on the number of hours from now. (in
	 * case of update, the posting time is recalculated starting from edition
	 * time)
	 * 
	 * @param reminder
	 */
	public void calculatePostingTime(Reminder reminder) {
		DateTime time = new DateTime();
		time = time.plusHours(reminder.getHoursUntil());
		reminder.setPostingTime(time.toDate());
	}

	@Scheduled(fixedRate = 15000)
	public void postDues() {
		List<Reminder> notConsumed = dao.readNotConsumed(100);
		ObjectMapper mapper = new ObjectMapper();
		log.info("processing " + notConsumed.size() + " reminders ...");
		for (Reminder reminder : notConsumed) {
			RestTemplate restTemplate = new RestTemplate();
			String jsonString;
			try {
				jsonString = mapper.writeValueAsString(reminder.getText());
			} catch (JsonProcessingException e) {
				reminder.setConsumed('F');
				log.error(" posting of reminder whose id is =" + reminder.getId() + " failed!");
				log.error(" reason: the reminders' text was not properly serualized: " + e.getMessage());
				continue;
			}
			String posting = restTemplate.postForObject(reminder.getUrl(), jsonString, String.class);
			log.info(" posted reminder whose id is =" + reminder.getId());
			log.info(" response was =" + posting);
			reminder.setConsumed('Y');
		}
	}

}
