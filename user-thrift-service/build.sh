#!/usr/env bash
docker build -t  user-service:latest .
docker tag user-service:latest 47.97.167.185:9999/article-service/user-service:latest
docker push 47.97.167.185:9999/article-service/user-service:latest