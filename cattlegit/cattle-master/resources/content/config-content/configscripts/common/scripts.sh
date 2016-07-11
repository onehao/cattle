set -e

check_debug()
{
    if [ -n "$CATTLE_SCRIPT_DEBUG" ] || echo "${@}" | grep -q -- --debug; then
        export CATTLE_SCRIPT_DEBUG=true
        export PS4='[${BASH_SOURCE##*/}:${LINENO}] '
        set -x
    fi
}

debug_on()
{
    CATTLE_SCRIPT_DEBUG=true check_debug
}

info()
{
    echo "INFO:" "${@}"
}

error()
{
    echo "ERROR:" "${@}" 1>&2
}

call_curl()
{
    local curl="curl -s --connect-timeout 20"
    if [ "$CURL_AUTH" = "false" ]; then
        $curl "$@"
    elif [ -n "$CATTLE_AGENT_INSTANCE_AUTH" ]; then
        $curl -H "Authorization: $CATTLE_AGENT_INSTANCE_AUTH" "$@"
    elif [ -n "$CATTLE_ACCESS_KEY" ]; then
        $curl -u ${CATTLE_ACCESS_KEY}:${CATTLE_SECRET_KEY} "$@"
    else
        $curl "$@"
    fi
}

get()
{
    if [ "$1" = "--no-auth" ]; then
        shift 1
        CURL_AUTH=false call_curl -L "$@"
    else
        call_curl -L "$@"
    fi
}

post()
{
    call_curl -X POST "$@"
}

put()
{
    call_curl -X PUT "$@"
}

get_config()
{
    ${CATTLE_HOME}/config.sh "$@"
}

reload_monit()
{
    local monit=$(pidof monit || true)
    if [ -n "$monit" ]; then
        info Reloading monit
        kill -HUP $monit
    fi
}

stage_files()
{
    local monit=false

    if [ -d content-home ]; then
        find content-home -name "*.sh" -exec chmod +x {} \;
        tar cf - -C content-home . | tar xvf - --no-overwrite-dir -C $CATTLE_HOME | sed 's/^/INFO: HOME -> /'
    fi

    if [ -d content ]; then
        for i in content/etc/init.d/*; do
            if [ -f $i ]; then
                chmod +x $i
            fi
        done

        for i in content/etc/monit/conf.d/*; do
            if [ -f $i ]; then
                monit=true
                chmod 600 $i
            fi
        done
                                
        find content -name "*.sh" -exec chmod +x {} \;
        tar cf - -C content . | tar xvf - --no-overwrite-dir -C / | sed 's/^/INFO: ROOT -> /'
    fi

    if [ "$monit" = "true" ]; then
        reload_monit
    fi
}

script_env()
{
    if [ -n "$CATTLE_CONFIG_URL" ]; then
        return
    fi

    # This is just using the routing tables to determine the primary IP or gateway
    # of the box.  No traffic is sent to 8.8.8.8 and it doesn't matter if 8.8.8.8
    # is accessible
    if [ "$CATTLE_AGENT_INSTANCE" = "true" ]; then
        local host=$(ip route get 8.8.8.8 | grep src | awk '{print $3}')
    else
        local host=$(ip route get 8.8.8.8 | grep src | awk '{print $7}')
    fi
    CATTLE_CONFIG_URL="${CATTLE_CONFIG_URL_SCHEME:-http}"
    CATTLE_CONFIG_URL="${CATTLE_CONFIG_URL}://${CATTLE_CONFIG_URL_HOST:-$host}"
    CATTLE_CONFIG_URL="${CATTLE_CONFIG_URL}:${CATTLE_CONFIG_URL_PORT:-9342}"
    CATTLE_CONFIG_URL="${CATTLE_CONFIG_URL}${CATTLE_CONFIG_URL_PATH:-/v1}"

    export CATTLE_CONFIG_URL
}

reply()
{
    jq -c '
        {
            "id" : "'$(uuidgen)'",
            "time" : '$(($(date '+%s') * 1000))',
            "name" : .replyTo,
            "data" : {},
            "previousNames" : [ .name ],
            "previousIds" : [ .id ],
            "resourceId" : .resourceId,
            "resourcetype" : .resourceType
        }
    '
}

apply_config()
{
    local file=${@:${#}:1}
    local cmd=${@:1:$(($#-1))}

    if ! ${cmd[@]} content-home/${file}; then
        if [ -e ${CATTLE_HOME}/${file} ]; then
           ${cmd[@]} ${CATTLE_HOME}/${file}
        fi
        return 1
    fi
}

add_route_table()
{
    local id=$1
    local opts=$2
    if ! ip rule show | cut -f1 -d: | grep -q '^'$id'$'; then
        ip rule add $2 table $id pref $id
    fi
}

reload_haproxy()
{
    CONFIG=$1

    if [ ! -f /var/run/haproxy.pid ]; then
        return 0
    fi

    # apply new config
    if haproxy -p /var/run/haproxy.pid -f $CONFIG -sf $(cat /var/run/haproxy.pid); then
        return 0
    else
        return 1
    fi
}

check_debug
script_env

export CATTLE_HOME=${CATTLE_HOME:-/var/lib/cattle}
export CATTLE_CONFIG_URL=${CATTLE_CONFIG_URL:-$CATTLE_URL}
export CATTLE_STORAGE_URL=${CATTLE_STORAGE_URL:-$CATTLE_URL}
