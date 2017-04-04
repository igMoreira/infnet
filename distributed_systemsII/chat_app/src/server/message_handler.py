import json
import threading
from datetime import datetime
from random import randint

__server_id = '0'
__server_joke_starts = ['você sabe', 'bará', 'repete', 'Noé', 'Mam']
__server_joke = {
    'piada': lambda: 'Toc, toc',
    'quem é?': lambda: __server_joke_starts[randint(0, len(__server_joke_starts)-1)],
    'quem eh?': lambda: __server_joke_starts[randint(0, len(__server_joke_starts)-1)],
    'você sabe quem?': lambda: 'EXATAMANTE!',
    'voce sabe quem?': lambda: 'EXATAMANTE!',
    'bará quem?': lambda: 'BARÁQUEM OBAMA!',
    'bara quem?': lambda: 'BARÁQUEM OBAMA!',
    'noé quem?': lambda: 'Noé da sua conta!',
    'noe quem?': lambda: 'Noé da sua conta!',
    'noeh quem?': lambda: 'Noé da sua conta!',
    'repete quem?': lambda: 'quem ' * randint(3, 10),
    'mam quem?': lambda: 'Mam-da nudes ( ͡° ͜ʖ ͡°)',
}
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

    if dst == __server_id and data.lower() in __server_joke:
        data = __server_joke[data]()
        dst = id
        id = __server_id

    if not dst in __connected_clients:
        __connected_clients[dst] = []
    __connected_clients[dst].append({'src':id, 'data':data})
    response = {'id': 0, 'msgNr': (msgNr + 1)}
    return (json.dumps(response) + '\n').encode('utf-8')


def receive(request):
    id = request['id']; msgNr = request['msgNr']
    data = __connected_clients.get(id, [])
    response = {'id': 0, 'msgNr': (msgNr + 1), 'data': data}
    __connected_clients[id] = []
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