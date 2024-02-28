FROM tomcat:9

COPY target/simple-customer-tracker-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/simple-customer-tracker.war

CMD ["catalina.sh", "run"]