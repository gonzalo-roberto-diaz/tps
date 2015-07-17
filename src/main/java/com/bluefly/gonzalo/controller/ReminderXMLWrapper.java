
package com.bluefly.gonzalo.controller;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "reminder")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reminder", propOrder = {
        "id",
        "scheduledTime",
        "url",
        "text",
        "consumed"
})
public class ReminderXMLWrapper {

    @XmlElement(required = true)
    protected Long id;
    @XmlElement(required = true)
    protected Date scheduledTime;
    @XmlElement(required = true)
    protected String url;
    @XmlElement(required = true)
    protected String text;
    @XmlElement(required = true)
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
