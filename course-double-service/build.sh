#!/usr/env bash
docker build -t  course-service:latest .
docker tag course-service:latest 47.97.167.185:9999/article-service/course-service:latest
docker push 47.97.167.185:9999/article-service/course-service:latest