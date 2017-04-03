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
    return json.dumps(response).encode('utf-8')


def send(request):
    id = request['id']; msgNr = request['msgNr']; dst = request['dest']; data = request['data']

    if not dst in __connected_clients:
        __connected_clients[dst] = []
    __connected_clients[dst].append({'src':id, 'data':data})
    response = {'id': 0, 'msgNr': (msgNr + 1)}
    return json.dumps(response).encode('utf-8')


def receive(request):
    pass

def handle(request, callback):
    request = json.loads(request)
    print("[%s][%s] - Received request : %s" % (datetime.now().strftime('%d/%m/%Y:%H:%M:%S'), request['cmd'], request))

    if request['cmd'] == 'login':
        response = login(request)
    if request['cmd'] == 'enviar':
        response = send(request)
    if request['cmd'] == 'receber':
        response = receive(request)

    t = threading.Thread(target=callback, args=(response,))
    print("[%s][%s] - Sending response : %s" % (datetime.now().strftime('%d/%m/%Y:%H:%M:%S'), request['cmd'], response.decode('utf-8')))
    t.start()


