import json
import threading

__connected_clients = {}

def login(request):
    id = request['id']; msgNr = request['msgNr']
    if id in __connected_clients:
        data = __connected_clients[id]
    else:
        data = __connected_clients[id] = []
    response = {'id':0, 'msgNr':(msgNr+1), 'data':data}
    return json.dumps(response).encode('utf-8')


def handle(data, callback):
    data = json.loads(data)
    print("Received message: %s " % (data,) )

    if data['cmd'] == 'login':
        t = threading.Thread(target=callback, args=(login(data),))
    if data['cmd'] == 'enviar':
        t = threading.Thread(target=callback, args=(b'{"id":"0", "msgNr":27}',))
    if data['cmd'] == 'receber':
        t = threading.Thread(target=callback, args=(b'{"id": "0","msgNr": 28,"data": [{"src":"maria","data":"oi!"},{"src":"maria","data":"kd vc?"}]}',))
    t.start()