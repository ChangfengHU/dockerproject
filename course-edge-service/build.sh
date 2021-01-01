#!/usr/env bash
docker build -t  course-edge-service:latest .
docker tag course-edge-service:latest 47.97.167.185:9999/article-service/course-edge-service:latest
docker push 47.97.167.185:9999/article-service/course-edge-service:latest