#!/bin/bash


curl -X POST -H "Content-Type: application/json" -d @test-jobs/mongo.json http://localhost:7000/controller/job

