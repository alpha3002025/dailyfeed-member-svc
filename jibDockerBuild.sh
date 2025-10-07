#!/bin/bash
./gradlew clean :dailyfeed-member:jibDockerBuild --info
docker push alpha300uk/dailyfeed-member-svc:0.0.1
