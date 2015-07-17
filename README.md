# tps
An example REST application that receives reminder messages, and resubmits them after specific periods of time.

This is a Java web application able to perform some REST-style CRUD operations on a repository of TPS reports, henceforth called _Reminders_

It consists of:

* a Spring application context
* an Hibernate back-end
* a controller that exposes REST services
* a business services layer that polls Reminders received, and posts them elsewhere

##Prerequisites
In order to run this application, you need a system with:

* Java 1.7 or higher 
* A connection to an empty database, with a user with DDL rights (the example is configured for a mysql DB called "tps"
* Apache Maven
* Git

##Installation
* clone the repository locally:
  `git clone https://github.com/gonzalo-roberto-diaz/tps.git`
* build and test the application:   
   `mvn clean install`
* run the embedded server:  
   `mvn jetty:run`
   
This makes the application avaliable on http://localhost:8080/tps

##Usage
###Posting new reminders
In order to add a reminder to the database, the application expects a POST to `http://localhost:8080/tps/create` with the following format:

```
{
  "text" : "The text of my TPS" , 
  "url" : "http://clarkatiempo.com/bluefly/target.php" , 
  "hoursUntil" : "10"
}
```

Where _text_ is the text of the reminder, _url_ is the destination it will be posted to, once its time is due, and _hoursUntil_ is in how many hours from now it should be posted to that target.

By the way, the address `http://clarkatiempo.com/bluefly/target.php` is real. It is a simple php page that I created in one mf my sites. which expects some JSON post with a single "message" field, and echoes it back with the same field plus the submission time. Of course, any other valid REST destination would also work.

If you want the submission to this target to occur immediately, just use 0 as the value of _hoursUntil_

### Get all reminders
To obtain the list of all posted reminders (both consumed and not consumed), GET from `http://localhost:8080/tps/all?start=0&size=3`

_start_  and _size_  are pagination parameters.

### Update a specific reminder
Updates are accepted for each of the 3 main fields separately. 

* To update the number of hours until submission, PUT to:
   `http://localhost:8080/tps/updateHoursUntil?id=1&newHoursUntil=48`
   
   _Note: The submission time is always recalculated based on the post time = the number of hours_
   
* To update the text content, PUT to: 
  `http://localhost:8080/tps/updateText?id=1&newText="This is my new text"`
  
* To update the URL, PUT to: `http://localhost:8080/tps/updateUrl?id=1&newUrl="http://posttestserver.com"`

### Delete a reminder

Simply issue a DELETE specifying the reminder ID: 
`http://localhost:8080/tps/delete?id=1`


##Configuration 
This little application is totally xml-less.
There is very little to configure, and all is in an external property file located at:  

  `tps/src/main/resources/application.properties`
  
 Its main sections are: 
 
 * __connection properties__ : the database access propeties.
 * __hibernate configuration__:  some basic Hibernate settings. Set _hbm2ddl.auto_ to `update` if you don't want the database data to be cleaned on every restart
 * __job__ : 
   * `cron.expression` indicates how frequently the polling for reminders occurs (the default is every 15 seconds)
   * `dues.batch.size` indicates the maximum chunk of reminders we can process on every pass (default is 100)
   
   
 
 
  
  
  
  

