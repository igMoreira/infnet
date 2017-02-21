# Echo server program
import socket
import sys
import json

HOST = 'localhost'               # Symbolic name meaning all available interfaces
PORT = 8080              # Arbitrary non-privileged port
s = None
for res in socket.getaddrinfo(HOST, PORT, socket.AF_UNSPEC,
                              socket.SOCK_STREAM, 0, socket.AI_PASSIVE):
    af, socktype, proto, canonname, sa = res
    try:
        s = socket.socket(af, socktype, proto)
    except socket.error as msg:
        s = None
        continue
    try:
        s.bind(sa)
        s.listen(1)
    except socket.error as msg:
        s.close()
        s = None
        continue
    break
if s is None:
    print('could not open socket')
    sys.exit(1)

print('Server is waiting requests...')
conn, addr = s.accept()
# print( 'Connected by ' + addr)
while True:
    data = conn.recv(2048).decode('utf-8')
    if not data: print("Client has disconnected"); break

    data = json.loads(data)
    print("Received message: %s " % (data,) )

    if data['cmd'] == 'login':
        conn.send(b'{"id":"test", "msgNr":1, "data":[{"src":"maria", "data":"oi!"}, {"src":"maria", "data":"kd vc?"}]}')
    if data['cmd'] == 'enviar':
        conn.send(b'{"id":"0", "msgNr":27}')
conn.close()