from threading import Thread, Lock
from datetime import datetime
import _thread
import time
import sys
import itertools
from message_handler import MessageHandler

_MENU_HEADER = """
-----------------------------------
 WELCOME TO THE BEST EVER CHAT APP
-----------------------------------

"""

_WAITING_LOGIN = 0
_LOGGING_IN = 1
_RUNNING = 2
_LOGOFF = 3

_SPINNER = itertools.cycle(['⠋','⠙','⠹','⠸','⠼','⠴','⠦','⠧','⠇','⠏'])

print('trying to connect to {0}:{1}'.format(sys.argv[1], sys.argv[2]))
_MESSAGE_HANDLER = MessageHandler(sys.argv[1], int(sys.argv[2]))

def login(usr, num):
    time.sleep(2)
    return {
        "id": "0",
        "msgNr": 54,
        "data": [
            {
                "src": "maria",
                "data": "oi!"
            },
            {
                "src": "maria",
                "data": "kd vc?"
            }
        ]
    }

def send(usr, num, recvr, msg):
    return {
        "id": "0",
        "msgNr": num + 1
    }

def get(usr, num):

    return {
        "id": "0",
        "msgNr": num + 1,
        "data": [
            {
                "src": "maria",
                "data": "oi {0}!".format(usr) 
            },
            {
                "src": "maria",
                "data": "kd vc?"
            }
        ]
    }

class Screen():

    def __init__(self):
        self.messages = []
        self.input = ''
        self.lock = Lock()
    
    def push_message(self, message):
        with self.lock:
            self.messages.append(message)

    def print(self):
        content = ''
        for m in self.messages:
            content += 'm' + '\n'
        print(content)

class bcolors:
    HEADER = '\033[95m'
    OKBLUE = '\033[94m'
    OKGREEN = '\033[92m'
    WARNING = '\033[93m'
    FAIL = '\033[91m'
    ENDC = '\033[0m'
    BOLD = '\033[1m'
    UNDERLINE = '\033[4m'

class Presentation(Thread):

    def __init__(self):
        Thread.__init__(self)
        self.is_active = False
        self.state = _WAITING_LOGIN
        self.data_to_show = None
        self.screen = Screen()
        self.usr = ''
        self.msg_number = 0

    def print_line(self, messages):
        print('')
        for m in messages:
            line = '[ {0} ] {3}{1}{4} >> {2}'.format(
                datetime.now().strftime('%H:%M:%S'),
                m['src'],
                m['data'],
                bcolors.OKGREEN,
                bcolors.ENDC
            )
            # self.screen.push_message(line)
            print(line)

    def handle_response(self, response):
        # print(response.__dict__)
        if response.data:
            self.print_line(response.data)

        # self.msg_number = response['msgNr']
        
    def async_login(self, usr):
        def _login(usr, clbk):
            # time.sleep(2)
            resp = _MESSAGE_HANDLER.login(usr)
            clbk(resp)

        def _callback(response = None):
            if response:
                # self.data_to_show = response
                print('\nlogin succeeded')
                print('~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n')
                self.handle_response(response)
                self.state = _RUNNING
            else:
                self.state = _WAITING_LOGIN

        _thread.start_new_thread(_login, (usr, _callback))
    

    def login(self):
        valid_state = self.state == _WAITING_LOGIN or self.state == _LOGGING_IN
        while self.is_active:
            if self.state == _WAITING_LOGIN:
                usr = input('username: ')

                sys.stdout.write('trying to login as {0}  '.format(usr))
                sys.stdout.flush()
                sys.stdout.write('\b')
                
                self.async_login(usr)
                self.state = _LOGGING_IN
            
            elif self.state == _LOGGING_IN:
                time.sleep(.1)
                sys.stdout.write(next(_SPINNER))
                sys.stdout.flush()
                sys.stdout.write('\b')
            
            else:
                sys.stdout.write(' \n')
                sys.stdout.flush()
                break

    def handle_logoff(self):
        self.stop()
        self.state = _LOGOFF
        print(':: sys: bye bye')
    
    def handle_running(self):
        # self.screen.print()
        try:
            print(':: [send, get, out] > [data]   ||   send > hello from the other side!')
            while self.state == _RUNNING and self.is_active:
                data = input(':: ')
                sptd = data.split(' > ')
                cmd = sptd[0]

                if cmd == 'send' and len(sptd) > 1:
                    data = sptd[1]
                    resp = _MESSAGE_HANDLER.send(data=data)
                    # self.handle_response(resp)
                    if resp:
                        print(':: {0}sys{1}: message sent'.format(
                                bcolors.FAIL,
                                bcolors.ENDC
                            )
                        )
                elif cmd == 'get':
                    resp = _MESSAGE_HANDLER.receive()
                    # print(resp)
                    if resp:
                        self.handle_response(resp)
                elif cmd == 'out':
                    self.handle_logoff()
                else:
                    print(':: sys: invalid command')
        except:
            print("Unexpected error:", sys.exc_info()[0])
            raise
    def run(self):
        self.is_active = True
        try:
            self.login()
        
            self.handle_running()

            self.handle_logoff()
        except KeyboardInterrupt:
            self.stop()

    def stop(self):
        self.is_active = False
        self.join()



def main():
    try:
        p = Presentation()
        # p.is_active = True
        # p.usr = 'raf'
        # p.state = _RUNNING
        # p.msg_number = 20
        # p.handle_running()
        p.run()

        while True:
            pass
    except Exception:
        pass
    finally:
        if p.is_active:
            p.stop()

if __name__ == '__main__':
    main()

