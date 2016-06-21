#!/bin/bash

. ${CATTLE_HOME:-/var/lib/cattle}/common/scripts.sh

reload_service()
{
    local service=$1
    local port=$2
    if [ ! -e /etc/init.d/${service} ]; then
        # ${service} is not yet installed
        return
    fi

    PID=$(pidof ${service} || true)

    if [ -z "$PID" ]; then
        /etc/init.d/${service} start
    elif [ -e ${CATTLE_HOME}/etc/cattle/${service}/http-reload ]; then
        curl -sf -X POST http://localhost:${port}/v1/reload
    else
        kill -HUP $PID
    fi
}

DNS=

for address in $(cat /etc/resolv.conf  | sed 's/^# nameserver/nameserver/' | grep ^nameserver | awk '{print $2}'); do
    if [ "$address" = "169.254.169.250" ]; then
        continue
    fi

    if [ -z "$DNS" ]; then
        DNS="\"${address}\""
    else
        DNS="${DNS}, \"${address}\""
    fi
done

sed -i "s/\"PARENT_DNS\"/$DNS/g" content-home/etc/cattle/dns/answers.json

stage_files

ip addr add 169.254.169.250/32 dev eth0 2>/dev/null || true
if ! ip route show | grep -q 169.254.169.254; then
    ip route add 169.254.169.254/32 dev eth0 via $(ip route get 8.8.8.8 | grep via | awk '{print $3}')
fi

reload_service rancher-dns 8113
reload_service rancher-metadata 8112
