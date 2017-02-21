import messages
import manager

class MessageHandler(object):

    __msgNr = 0

    def __init__(self, host, port):
        if host and port:
            self.__client_manager = manager.ClientManager(host, port)
        else:
            raise RuntimeError("A host and port are required to create a MessageHandler")



    def login(self, id):
        response = None
        if id:
            request = messages.LoginRequest(id=id, msgNr=MessageHandler.__msgNr)
            response = self.__client_manager.make_request(request)
            MessageHandler.__msgNr = response.msgNr
        return response


    def send(self, id="0", dst=None, data=None):
        response = None
        if dst and data:
            request = messages.SendRequest(id=id, msgNr=MessageHandler.__msgNr, dst=dst, data=data)
            response = self.__client_manager.make_request(request)
            MessageHandler.__msgNr = response.msgNr
        return response