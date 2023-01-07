# Mutual SSL Server

## Run

### Client docker

docker build --build-arg JAR_FILE=build/libs/\*.jar -t mutual-ssl-client . --no-cache --progress plain

docker run --name=client -d -p 8085:8081 --hostname client.suriya.com  mutual-ssl-client

### Server docker

docker build --build-arg JAR_FILE=build/libs/\*.jar -t mutual-ssl-server . --no-cache --progress plain

docker run --name=server -d -p 8086:8082 --hostname server.suriya.com mutual-ssl-server 

### Getting server ip

docker inspect client | grep IPAddress

docker inspect server | grep IPAddress

### Exec

docker exec -it client sh

