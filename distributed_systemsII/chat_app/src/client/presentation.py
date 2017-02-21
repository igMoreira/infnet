from threading import Thread, Lock
from datetime import datetime
import _thread
import time
import sys
import itertools
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

def login(usr, num):
    time.sleep(5)
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
    pass

def get(usr, num):
    pass

class Screen():

    def __init__(self):
        self.messages = []
        self.input = ''
        self.lock = Lock()
    
    def push_message(self, message):
        with self.lock:
            self.messages.append(message)

    def print(self):
        pass


class Presentation(Thread):

    def __init__(self):
        Thread.__init__(self)
        self.state = _WAITING_LOGIN
        self.data_to_show = None
        self.screen = Screen()

    def print_line(self, messages):
        for m in messages:
            line = '[ {0} ] {1} >> {2}'.format(
                datetime.now().strftime('%H:%M:%S'),
                m['src'],
                m['data']
            )
            self.screen.push_message(line)
            print(line)

    def async_login(self, usr, num):
        def _login(usr, num, clbk):
            resp = login(usr, num)
            clbk(resp)

        def _callback(response = None):
            if response:
                # self.data_to_show = response
                self.print_line(response['data'])
                self.msg_number = response['msgNr']
                self.state = _RUNNING
            else:
                self.state = _WAITING_LOGIN

        _thread.start_new_thread(_login, (usr, num, _callback))

    

    def login(self):
        valid_state = self.state == _WAITING_LOGIN or self.state == _LOGGING_IN
        while self.is_active:
            if self.state == _WAITING_LOGIN:
                usr = input('username: ')

                sys.stdout.write('trying to login as {0}  '.format(usr))
                sys.stdout.flush()
                sys.stdout.write('\b')
                
                self.async_login('foo', 2)
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
        pass
    
    def handle_running(self):
        try:
            self.screen.print()


            while self.state == _RUNNING and self.is_active:
                pass
        except KeyboardInterrupt:
            pass
        finally:
            pass

    def run(self):
        self.is_active = True
        try:
            self.login()
            print('login succeeded')
        
            self.handle_running()

            self.handle_logoff()
        except KeyboardInterrupt:
            self.stop()
        finally:
            print('presentation has ended')

    def stop(self):
        self.is_active = False
        self.join()



def main():
    try:
        p = Presentation()
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

