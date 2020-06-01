from flask import Blueprint, jsonify
import app.Person as pd

person_api = Blueprint('person', __name__)


@person_api.route('/person/sachin', methods=['GET'])
def process_person():
    pd1 =  pd.PersonData('sachin',10)
    #data = {pd1, 'new test'}
    return jsonify(pd1.age)


def get_data(a,b):
    return a+b