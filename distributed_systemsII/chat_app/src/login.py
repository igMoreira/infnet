from json import dumps, loads


class LoginRequest():
    def __init__(self, id = None, msgNr = None):
        self.cmd = 'login'
        self.id = id
        self.msgNr = msgNr
    
    def to_json(self):
        return dumps(self.__dict__)

    @staticmethod
    def from_json(serialized):
        if serialized:
           return LoginRequest(**loads(serialized))



def main():
    login = LoginRequest.from_json('{ "id": "rafael", "msgNr": 10 }')
    print(login.__dict__)
    print(login.to_json())

if __name__ == '__main__':
    main()