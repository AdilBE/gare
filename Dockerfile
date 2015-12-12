FROM tomcat:8.0

# Maintainer
MAINTAINER Adil BENDOULA <adil.bendoula@gmail.com>


# Deploy Gare
COPY /home/ubuntu/gare/target/gare-webservice.war $CATALINA_HOME/webapps/
