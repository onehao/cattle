# cattle
refactor version of cattle

DOCKER SWARM STRATEGIES
http://onehao.pw:9004/2016/06/27/docker-swarm-strategies/

Agents make an outbound websocket connection to cattle. This long-lived bi-directional websocket connection serves as an event bus over which cattle pushes events for container create, start, stop, and delete, along with a number of other events. The code that makes the websocket connection and handles the events is here: https://github.com/rancher/python-agent/blob/master/cattle/agent/event.py

'''
  GET http://host:8080/v1/projects/1a9/subscribe?eventNames=resource.change&eventNames=service.kubernetes.change&limit=-1&include=hosts&include=services&include=instances&include=instance&include=instanceLinks&include=ipAddresses&sockId=1 HTTP/1.1
Host: host:8080
Connection: Upgrade
Pragma: no-cache
Cache-Control: no-cache
Upgrade: websocket
Origin: http://host:8080
Sec-WebSocket-Version: 13
User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36
Accept-Encoding: gzip, deflate, sdch
Accept-Language: en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4
Cookie: PL=rancher; CSRF=E338A340DB90E7F8BD71CCD03E8539FB
Sec-WebSocket-Key: v1JoPVPKFCPFaO7KbRWIuA==
Sec-WebSocket-Extensions: permessage-deflate; client_max_window_bits


HTTP/1.1 101 Switching Protocols
Date: Thu, 28 Jul 2016 02:58:47 GMT
X-API-Schemas: http://host:8080/v1/projects/1a9/schemas
X-API-ACCOUNT-ID: 1a9
X-API-Client-IP: fe80:0:0:0:4551:def4:928f:9dd2%11
Set-Cookie: CSRF=E338A340DB90E7F8BD71CCD03E8539FB;Path=/
Expires: Thu, 01 Jan 1970 00:00:00 GMT
Connection: Upgrade
Sec-WebSocket-Accept: GnEabl6wFtKVq0yj0xE6WrSnHD4=
Upgrade: WebSocket
Content-Type: text/plain

'''
