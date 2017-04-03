import json
import threading
from datetime import datetime

__connected_clients = {}

def login(request):
    id = request['id']; msgNr = request['msgNr']
    if id in __connected_clients:
        data = __connected_clients[id]
    else:
        data = __connected_clients[id] = []
    response = {'id':0, 'msgNr':(msgNr+1), 'data':data}
    return (json.dumps(response) + '\n' ).encode('utf-8')


def send(request):
    id = request['id']; msgNr = request['msgNr']; dst = request['dst']; data = request['data']

    if not dst in __connected_clients:
        __connected_clients[dst] = []
    __connected_clients[dst].append({'src':id, 'data':data})
    response = {'id': 0, 'msgNr': (msgNr + 1)}
    return (json.dumps(response) + '\n').encode('utf-8')


def receive(request):
    id = request['id']; msgNr = request['msgNr']
    data = __connected_clients.get(id, [])
    response = {'id': 0, 'msgNr': (msgNr + 1), 'data': data}
    return (json.dumps(response) + '\n').encode('utf-8')


def handle(conn):
    while True:
        request = conn.recv(2048).decode('utf-8')

        if request:
            request = json.loads(request)

            print("[%s][%6s] - Received request : %s" % (datetime.now().strftime('%d/%m/%Y:%H:%M:%S'), request['cmd'], request))

            if request['cmd'] == 'login':
                response = login(request)
            if request['cmd'] == 'enviar':
                response = send(request)
            if request['cmd'] == 'receber':
                response = receive(request)

            print("[%s][%6s] - Sending response : %s" % (datetime.now().strftime('%d/%m/%Y:%H:%M:%S'), request['cmd'], response.decode('utf-8')))
            conn.send(response)
        else:
            print("[%s][%6s] : %s" % (datetime.now().strftime('%d/%m/%Y:%H:%M:%S'), 'logoff', 'Client has disconnected'))
            conn.close()
            break