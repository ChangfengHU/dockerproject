#!/usr/env bash
docker build -t  user-edge-service:latest .
docker tag user-edge-service:latest 47.97.167.185:9999/article-service/user-edge-service:latest
docker push 47.97.167.185:9999/article-service/user-edge-service:latest