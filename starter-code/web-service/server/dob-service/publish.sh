#!/bin/sh
cd dob
sudo rm -rf /usr/local/tomcat9/webapps/axis2/WEB-INF/services/DobService.aar 
sleep 1m
javac com/web/DobService.java
jar cvf DobService.aar *
sudo mv DobService.aar /usr/local/tomcat9/webapps/axis2/WEB-INF/services/

