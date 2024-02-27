FROM tomcat:9

COPY target/jsysdb-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/jsysdb.war

CMD ["catalina.sh", "run"]