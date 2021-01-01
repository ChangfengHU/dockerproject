#!/usr/env bash
docker run -d --net=host \
  --hostname=47.97.167.185 \
  -e MESOS_PORT=5050 \
  -e MESOS_ZK=zk://47.96.141.35:2181/mesos \
  -e MESOS_QUORUM=1 \
  -e MESOS_REGISTRY=in_memory \
  -e MESOS_LOG_DIR=/var/log/mesos \
  -e MESOS_WORK_DIR=/var/tmp/mesos \
  -v "$(pwd)/log/mesos:/var/log/mesos" \
  -v "$(pwd)/tmp/mesos:/var/tmp/mesos" \
  mesosphere/mesos-master:1.7.1