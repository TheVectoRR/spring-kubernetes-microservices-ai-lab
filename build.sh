#!/bin/bash

mvn clean install -DskipTests
skaffold dev

#skaffold delete
