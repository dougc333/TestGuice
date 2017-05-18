#!/bin/bash


curl -X POST -H "Content-Type: application/json" -d@mongo.json http://localhost:7000/controller/job

