#!/usr/bin/env bash
docker stop vyibc-mysql
docker rm vyibc-mysql
docker run --name vyibc-mysql  -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456789 -d mysql:5.7
