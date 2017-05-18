#!/bin/bash


curl -vX POST -H "Content-Type: application/json" -d @examples/multi-db/test-jobs/mongo.json http://localhost:7000/controller/job

