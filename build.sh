#!/bin/bash

mvn clean install -DskipTests
skaffold dev

# remove the created kubernetes cluster
# skaffold delete

# cleanup unused docker images (created by skaffold)
# docker image prune -af

# to run the jobs:
# kubectl apply -f k8s/jobs/questiondb-seed-job.yaml
# kubectl apply -f k8s/jobs/questiondb-wipe-job.yaml
