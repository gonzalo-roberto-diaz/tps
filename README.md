# tps
An example REST application that receives and polls reminders.

This is a Java web application able to perform some REST-style CRUD operations on a repository of TPS reports, henceforth called _Reminders_

It consists of:

* a Spring application context
* an Hibernate back-end
* a controller that exposes REST services
* a business services layer that polls Reminders received, and posts them elsewhere

 
