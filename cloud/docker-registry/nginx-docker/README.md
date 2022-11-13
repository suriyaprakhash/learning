```
docker build -t html-nginx:v1 .
```

```
docker run -d -p 80:80 html-nginx:v1
```

```
curl localhost:80
```