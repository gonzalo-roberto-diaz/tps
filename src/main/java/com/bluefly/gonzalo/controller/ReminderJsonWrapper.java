
package com.bluefly.gonzalo.controller;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ReminderJsonWrapper {

	@JsonProperty("id")
    protected Long id;
	
	@JsonProperty("scheduledTime")
    protected Date scheduledTime;
	
	@JsonProperty("url")
    protected String url;
	
	@JsonProperty("text")
    protected String text;
	
	@JsonProperty("hoursUntil")
	protected int hoursUntil;
	
	public int getHoursUntil() {
		return hoursUntil;
	}

	public void setHoursUntil(int hoursUntil) {
		this.hoursUntil = hoursUntil;
	}

	@JsonProperty("consumed")
    protected Character consumed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Character getConsumed() {
        return consumed;
    }

    public void setConsumed(Character consumed) {
        this.consumed = consumed;
    }

}
