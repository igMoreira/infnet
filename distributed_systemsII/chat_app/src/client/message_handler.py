import messages
import manager

class MessageHandler(object):

    __msgNr = 0

    def __init__(self, host, port):
        if host and port:
            self.__client_manager = manager.ClientManager(host, port)
            self.__id = None
        else:
            raise RuntimeError("A host and port are required to create a MessageHandler")



    def login(self, id):
        response = None
        if id:
            request = messages.LoginRequest(id=id, msgNr=MessageHandler.__msgNr)
            response = self.__client_manager.make_request(request)
            if response:
                self.__id = id
                MessageHandler.__msgNr = response.msgNr
        return response


    def send(self, data, dst="0"):
        response = None
        if not self.__id:
            print('You have to be logged in to send messages!')
        else:
            if data:
                request = messages.SendRequest(id=self.__id, msgNr=MessageHandler.__msgNr, dst=dst, data=data)
                response = self.__client_manager.make_request(request)
                if response:
                    MessageHandler.__msgNr = response.msgNr
            else:
                print('The data to be sent cannot be empty!')
        return response


    def receive(self):
        response = None
        if not self.__id:
            print('You have to be logged in to send messages!')
        else:
            request = messages.ReceiveRequest(id=self.__id, msgNr=MessageHandler.__msgNr)
            response = self.__client_manager.make_request(request)
            if response:
                MessageHandler.__msgNr = response.msgNr
        return response


    def logout(self):