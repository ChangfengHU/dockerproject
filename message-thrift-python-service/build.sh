#!/usr/bin/env bash
docker build -t  message-service:latest .
docker tag message-service:latest 47.97.167.185:9999/article-service/message-service:latest
docker push 47.97.167.185:9999/article-service/message-service:latest