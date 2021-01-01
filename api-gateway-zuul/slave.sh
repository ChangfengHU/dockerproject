docker run -d --net=host --privileged \
  --hostname=47.96.141.35 \
  -e MESOS_PORT=5051 \
  -e MESOS_MASTER=zk://47.96.141.35:2181/mesos \
  -e MESOS_SWITCH_USER=0 \
  -e MESOS_CONTAINERIZERS=docker \
  -e MESOS_LOG_DIR=/var/log/mesos \
  -e MESOS_WORK_DIR=/var/tmp/mesos \
  -v "$(pwd)/log/mesos:/var/log/mesos" \
  -v "$(pwd)/tmp/mesos:/var/tmp/mesos" \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v /sys:/sys \
  -v /usr/bin/docker:/usr/local/bin/docker \
  mesosphere/mesos-slave:1.4.1 --no-systemd_enable_support \
  --no-hostname-lookup --hostname=47.96.141.35