#!/bin/bash

mvn clean install -DskipTests
skaffold dev

# remove the created kubernetes cluster
# skaffold delete

# cleanup unused docker images (created by skaffold)
# docker image prune -af
