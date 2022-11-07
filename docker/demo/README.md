Docker

```cmd
docker build -t docker-demo:latest --build-arg ARTIFACT_NAME=docker-demo.jar --build-arg CONTAINER_PORT=8080 --progress=plain --no-cache .
```

```cmd
docker build -f ./Dockerfile -e ARTIFACT_NAME=docker-demo.jar
```