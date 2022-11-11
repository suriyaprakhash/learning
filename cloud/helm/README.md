# HELM


## Running helm registry

Running a helm museum

```
docker pull chartmuseum/chartmuseum:latest
```

```
docker run --name local-helm-registry \
  --rm -it -d \
  -p 7000:8080 \
  -v $(pwd)/charts:/charts \
  -e DEBUG=true \
  -e STORAGE=local \
  -e STORAGE_LOCAL_ROOTDIR=/charts \
  chartmuseum/chartmuseum
```

curl http://localhost:7000/


## Running chart in OC

### mychart

```
helm create mychart
```


### OC

```
oc new-project my-helm-chart
```

```
helm install mychart my-helm-chart
```

https://v2.helm.sh/docs/chart_template_guide/
