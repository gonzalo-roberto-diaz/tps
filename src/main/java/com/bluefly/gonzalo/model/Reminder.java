package com.bluefly.gonzalo.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="REMINDER")
/**
 * Central domain class in this sample application
 * @author gdiaz
 *
 */
public class Reminder {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min=5, max=50)
	@Column(name = "URL", nullable = false)
	private String url;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
	@Column(name = "POSTING_TIME", nullable = false)
	private Date postingTime;

	@NotNull
	@Digits(integer=2, fraction=0)
	@Column(name = "HOURS_UNTIL", nullable = false)
	private Integer hoursUntil;
	
	@NotEmpty
	@Column(name = "TEXT", nullable = false)
	private String text;
	
	@Column(name = "CONSUMED")
	Character consumed = 'N';

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String name) {
		this.url = name;
	}

	public Date getPostingTime() {
		return postingTime;
	}

	public void setPostingTime(Date time) {
		this.postingTime = time;
	}

	public Integer getHoursUntil() {
		return hoursUntil;
	}

	public void setHoursUntil(Integer value) {
		this.hoursUntil = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String value) {
		this.text = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Reminder))
			return false;
		Reminder other = (Reminder) obj;
		if (id != other.id)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Reminder [id = " + id +  ", url =" + url);
		sb.append(", posting time = " + postingTime + ", hours until = " + hoursUntil);
		sb.append(", text = " + text + ", consumed =  " + consumed + "]");
		return sb.toString();
	}

	public Character getConsumed() {
		return consumed;
	}

	public void setConsumed(Character consumed) {
		this.consumed = consumed;
	}
	
	
	

}
