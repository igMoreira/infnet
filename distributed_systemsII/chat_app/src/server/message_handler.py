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
    pass


def handle(request, callback):
    request = json.loads(request)
    print("[%s][%s] - Received request : %s" % (datetime.now().strftime('%d/%m/%Y:%H:%M:%S'), request['cmd'], request))

    if request['cmd'] == 'login':
        response = login(request)
        t = threading.Thread(target=callback, args=(response,))
    if request['cmd'] == 'enviar':
        t = threading.Thread(target=callback, args=(b'{"id":"0", "msgNr":27}',))
    if request['cmd'] == 'receber':
        t = threading.Thread(target=callback, args=(b'{"id": "0","msgNr": 28,"data": [{"src":"maria","data":"oi!"},{"src":"maria","data":"kd vc?"}]}',))

    print("[%s][%s] - Sending response : %s" % (datetime.now().strftime('%d/%m/%Y:%H:%M:%S'), request['cmd'], response.decode('utf-8')))
    t.start()