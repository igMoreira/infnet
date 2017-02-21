import socket

import sys



class ClientManager(object):
    """
        This class is the orchestration between the UI
        commands and the commands execution itself.
    """

    __CONTEXT = socket.socket(socket.AF_INET, socket.SOCK_STREAM)



    def __init__(self, host, port):
        """
            Constructor
        """
        try:
            ClientManager.__CONTEXT.connect((host, port))
            self.id = None
            self.msgNr = 0
            print('Connected to remote host. You can now start sending messages!')
        except Exception as e:
            print('Unable to connect')
            print(e)
            sys.exit()



    def is_logged(self):
        """
            Verifies if the user is already logged with the server.
        :return:
        """
        return self.id != None



    def __send_message(self, message):
        """
            Sends a message through the socket.
        :param payload:
        :return:
        """
        self.__CONTEXT.sendall(message.encode())



    def __receive_response(self):
        return self.__CONTEXT.recv(2048)



    def make_request(self, request_message):
        response = None
        if self.is_logged():
            try:
                self.__send_message(request_message)
                response = request_message.build_response(self.__receive_response().decode())
            except Exception as e:
                print(e)
                print('Connection error while sending or receiving request!!!')
        return response

    