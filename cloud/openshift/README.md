# Openshift

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