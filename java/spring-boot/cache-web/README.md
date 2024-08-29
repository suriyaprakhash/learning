### Build

```shell
sudo docker build \
-t suriyaprakhash/cache-web:0.0.1 \
--build-arg SRC_CODE_PATH=home\suriya\projects\cache-web \
--no-cache --progress=plain .
```

```shell
sudo docker login -u suriyaprakash  
```

```shell
sudo docker-compose up -d
```