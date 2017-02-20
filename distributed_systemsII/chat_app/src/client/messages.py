from json import dumps, loads
import abc


class JsonMessage(object):
    """
        Abstract class used to parse a model to json and vice-versa.
    """
    __metaclass__ = abc.ABCMeta


    def to_json(self):
        """
            Converts the model to a json message.
        :return:
        """

        return dumps(self.__dict__)

    @classmethod
    def from_json(cls, payload):
        """
            Creates a Object instance based on the json payload.
        :param payload:
        :return:
        """
        if payload:
            return cls(**loads(payload))
        else:
            raise Exception("Empty payload!")



class Request(object):
    __metaclass__ = abc.ABCMeta

    def build_response(self, payload):
        return self.RESPONSE_CLASS.from_json(payload)


###############################################
#               Login Messages                #
###############################################

class LoginRequest(JsonMessage, Request):
    """
        Login Request message.
    """

    def __init__(self, id=None, msgNr=None):
        self.cmd = 'login'
        self.id = id
        self.msgNr = msgNr


class LoginResponse(JsonMessage):
    """
        Login Response message.
    """

    def __init__(self, id=None, msgNr=None, data=None):
        """
            Constructor
        """
        self.id = id
        self.msgNr = msgNr
        self.data = data

###############################################
#         END   Login Messages                #
###############################################