# Description
Rest API service with reactive documentation using swagger

continuous delivery example, each commit in this git repo enable a docker image build over circle ci and push the image result in docker hub.

Another hook exists on docker hub that enable the delivery of a new container on amazon based on the new created docker image over the docker cloud.
