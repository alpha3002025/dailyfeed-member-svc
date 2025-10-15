#!/bin/bash
./gradlew :dailyfeed-member:jibDockerBuild --info
docker push alpha300uk/dailyfeed-member-svc:beta-20251015-0001
