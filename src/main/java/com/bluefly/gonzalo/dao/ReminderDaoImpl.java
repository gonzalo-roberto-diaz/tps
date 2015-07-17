package com.bluefly.gonzalo.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.bluefly.gonzalo.model.Reminder;

@Repository("reminderDao")
public class ReminderDaoImpl extends AbstractDao<Integer, Reminder> implements ReminderDao {

	public Reminder findById(int id) {
		return getByKey(id);
	}

	public Reminder saveReminder(Reminder employee) {
		return persist(employee);
	}

	@SuppressWarnings("unchecked")
	public List<Reminder> findAllReminders(long start, long size) {
		Criteria criteria = createEntityCriteria();
		criteria.setFirstResult((int)start);
		criteria.setMaxResults((int)size);
		return (List<Reminder>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Reminder> readNotConsumed(int size) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("consumed", 'N'));
		criteria.setFirstResult(0);
		criteria.setMaxResults(size);
		return (List<Reminder>) criteria.list();
	}	
	
	public long count(){
		Criteria criteria = createEntityCriteria();
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}


}
