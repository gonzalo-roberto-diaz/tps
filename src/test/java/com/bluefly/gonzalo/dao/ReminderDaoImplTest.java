package com.bluefly.gonzalo.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bluefly.gonzalo.dao.ReminderDao;
import com.bluefly.gonzalo.model.Reminder;


public class ReminderDaoImplTest extends EntityDaoImplTest{

	@Autowired
	ReminderDao reminderDao;

	@Override
	protected IDataSet getDataSet() throws Exception{
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Reminder.xml"));
		return dataSet;
	}
	
	/* In case you need multiple datasets (mapping different tables) and you do prefer to keep them in separate XML's
	@Override
	protected IDataSet getDataSet() throws Exception {
	  IDataSet[] datasets = new IDataSet[] {
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Reminder.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Benefits.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Departements.xml"))
	  };
	  return new CompositeDataSet(datasets);
	}
	*/

	@Test
	public void findById(){
		Assert.assertNotNull(reminderDao.findById(1));
		Assert.assertNull(reminderDao.findById(3));
	}

	
	@Test
	public void saveReminder(){
		reminderDao.saveReminder(getSampleReminder());
		Assert.assertEquals(reminderDao.findAllReminders(0, 3).size(), 3);
	}
	



	@Test
	public void findAllReminders(){
		Assert.assertEquals(reminderDao.findAllReminders(0, 2).size(), 2);
	}


	public Reminder getSampleReminder(){
		Reminder reminder = new Reminder();
		reminder.setUrl("Karen");
		reminder.setText("12345");
		reminder.setHoursUntil(32);
		reminder.setPostingTime(new Date());
		return reminder;
	}

}
