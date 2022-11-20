# Openshift

## Trusting the images from private registry with the CA

https://docs.openshift.com/container-platform/4.7/cicd/builds/setting-up-trusted-ca.html

1. Creating the _configMap_

```
$ oc create configmap registry-cas -n openshift-config \
--from-file=myregistry.corp.com..5000=/etc/docker/certs.d/myregistry.corp.com:5000/ca.crt \
--from-file=otherregistry.com=/etc/docker/certs.d/otherregistry.com/ca.crt
```
2. Updating the cluster image config
```
$ oc patch image.config.openshift.io/cluster --patch '{"spec":{"additionalTrustedCA":{"name":"registry-cas"}}}' --type=merge
```

## Login 

To get token from https://oauth-openshift.apps-crc.testing/oauth/token/request
```
oc login
```
Use the token below,
```
oc login --token=sha256~J1av_PDyDd3D8lWhTZJ1Ym1JXaeTKLvSSZTCwmTyFzI --server=https://api.crc.testing:6443
```

## Installing from local helm package

Get all projects
```
oc get projects
```

To point the api to a project
```
oc project first-project
```

Displays the current status of the chart deployed in oc
```
helm list
```

This installs the helm chart from local folder to oc,  **helm install [NAME] [CHART] [flags]**
```
helm install newchart ./mychart
```

The following uninstalls the helm chart
```
helm delete newchart
```

## Uploading into image stream