import flask
from routes.script_api import script_api
from routes.person_api import person_api
main = flask.Flask(__name__)

main.register_blueprint(script_api)
print('testApi registered')
main.register_blueprint(person_api)
print('personApi registered')

main.config["DEBUG"] = False

print('Server is about to start')

main.run()