FROM tomcat:8.0.33-jre7

# Install app dependencies
COPY target/easy-router-engine.war /usr/local/tomcat/webapps/

EXPOSE 8080
CMD ["catalina.sh", "run"]