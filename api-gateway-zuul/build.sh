#!/usr/env bash
docker build -t  api-gateway-zuul:latest .
docker tag api-gateway-zuul:latest 47.97.167.185:9999/article-service/api-gateway-zuul:latest
docker push 47.97.167.185:9999/article-service/api-gateway-zuul:latest