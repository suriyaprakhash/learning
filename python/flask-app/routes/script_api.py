from flask import Blueprint, current_app, request, jsonify
from app.script import func_sum, set_global_data, get_global_data, get_res
from exception.AppException import ConvertFailed


script_api = Blueprint('api', __name__)

@script_api.route('/api', methods=['GET'])
def sample_test_api():
    return "<h1>Distant Reading Archive</h1><p>This site is a prototype API for distant reading of science fiction novels.</p>"


@script_api.route('/script-global-test', methods=['GET'])
def test_script_global_set_and_get():
    set_global_data('hello')
    return get_global_data()


# query param
# http://localhost:5000/script-sum?int1=5&int2=10
@script_api.route('/script-sum', methods=['GET'])
def test_script_func_sum():
    try:
        int1 = int(request.args.get('int1'))
        int2 = int(request.args.get('int2'))
        current_app.logger.debug('%d - int1 & %d - int2', int1, int2)
        # convert int to string and return
        sum_str_obj = {'sum_str': 'The sum is ' + str(func_sum(int1, int2))}
        #sum_str_obj = str(func_sum(int1, int2))
        payload = get_res(sum_str_obj)
        return jsonify(payload)
    # Catch ConvertFailed exception from get_res()
    except ConvertFailed:
        current_app.logger.error('Errored out due to dict init')
    # this is for generic error
    except:
        current_app.logger.error('Unexpected error occured')
    finally:
        current_app.logger.debug('sum function terminates now')

@script_api.app_errorhandler(404)
def page_not_found(e):
    """ Return error 404 """
    return 'Page not found from script-api'


@script_api.app_errorhandler(500)
def page_not_found(e):
    """ Return error 500 """
    return 'Internal server error from script-api'

