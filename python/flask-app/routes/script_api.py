from flask import Blueprint, current_app, request, jsonify
from app.script import func_sum, set_global_data, get_global_data
from exception import AppException

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
        payload = {'sum_str': 'The sum is ' + str(func_sum(int1, int2))}
        res = dict(payload or ())
        return jsonify(res)
    except:
        current_app.logger.error('Errored out')
        raise AppException('Input error', status_code=400, payload = 'payload')

#
# @script_api.app_errorhandler(AppException)
# def handle_error(error):
#     res = jsonify(error.to_dict())
#     res.status_code = error.status_code
#     return res
