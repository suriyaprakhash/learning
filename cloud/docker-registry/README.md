# Docker registry

## Exercise

```
docker pull registry
```

**Volume** is the folder as *docker-registry\volume*

```cmd
docker run -d --name local-registry -p 5000:5000 -v C:\Suriya\ws\learning\cloud\docker-registry\volume:/var/lib/registry registry:latest
```
Make sure you run from the right directory of the volume
```bash
docker run -d --name local-registry -p 5000:5000 -v $(pwd)/volume:/var/lib/registry registry:latest
```

## Reference

- Docker registry distribution [gitlab](https://github.com/distribution/distribution)
- [Docker hub registry](https://hub.docker.com/_/registry)