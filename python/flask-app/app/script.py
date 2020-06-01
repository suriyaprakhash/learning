global_data = 'first'


def get_global_data():
    return global_data


def set_global_data(data_param):
    # global_data is not considered global data
   global_data = data_param


def func_sum(a,b):
    return a+b