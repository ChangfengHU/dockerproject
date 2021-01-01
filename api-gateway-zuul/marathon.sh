docker run --net=host -d \
  mesosphere/marathon:latest \
  --master zk://127.0.0.1:2181/mesos \
  --zk zk://127.0.0.1:2181/marathon