from builtins import dict


class AppException(Exception):
    #status_code = 400  # Bad req

    def __int__(self, message, status_code=None, payload=None):
        Exception.__int__(self)
        self.message = message
        if status_code is not None:
            self.status_code = status_code
        self.payload = payload

    def to_dict(self):
        rv = dict(self.payload or ())
        rv['message'] = self.message
        return rv
