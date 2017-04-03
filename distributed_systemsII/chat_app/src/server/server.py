# Echo server program
import socket
import sys
import threading

import message_handler

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

while True:
    conn, addr = s.accept()
    threading.Thread(target=message_handler.handle, args=(conn,)).run()

conn.close()