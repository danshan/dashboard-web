#!/bin/sh

mvn -Denv=qa -U -B -DskipTests clean com.dianping.maven.plugins:dependency-check-plugin:check -Dchecklist=http://code.dianpingoa.com/api/v3/artifacts