FROM tomcat:8.0

# Maintainer
MAINTAINER Adil BENDOULA <adil.bendoula@gmail.com>


# Deploy Gare
COPY target/gare-webservice.war $CATALINA_HOME/webapps/