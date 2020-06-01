from exception.AppException import ConvertFailed

global_data = 'first'


def get_global_data():
    return global_data


def set_global_data(data_param):
    # global_data is not considered global data
   global_data = data_param


def func_sum(a,b):
    return a+b

def get_res(payload):
    res = {}
    try:
        res = dict(payload or ())
    except:
        raise ConvertFailed
    return res
