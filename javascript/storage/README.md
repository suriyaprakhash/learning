# Storage

Checking out storage options in the user agents.

## Why ?

This folder talks about the following topics
- Cookies
- Local storeage
- Session storeeage
- Indexd DB

## Running the code in Local

### Client

- Install [vs-live-server](https://github.com/ritwickdey/vscode-live-server/tree/428e01caf02bfa7ee75741df0f02fc9d2b5b0999#live-server)
- Click on manage extention and update the **liveServer.settings.https** with the following json,
```
{
    "tabnine.experimentalAutoImports": true,
    "files.autoSave": "afterDelay",
    "liveServer.settings.https": {
        "enable" : false,
        "cert": "C:\\Suriya\\ws\\learning\\javascript\\storage\\cert\\DomainCert-Client.pem",
        "key": "C:\\Suriya\\ws\\learning\\javascript\\storage\\cert\\DomainKey.pem",
        "passphrase": "suriya"
    },
    "liveServer.settings.ignoreFiles": [

        ".vscode/**",
        "**/*.scss",
        "**/*.sass",
        "**/*.ts"
    ]
}
```
- Click **Go Live**


### Server


Run the following to start the server,

```
node .\server\server.js
```
