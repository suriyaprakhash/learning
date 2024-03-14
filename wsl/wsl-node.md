# wsl-node

- [New installation process](#new-installation-process)
    + [CURL](#curl)
    + [Install NVM](#install-nvm)
    + [Install node & npm](#install-node--npm)
    + [Project Init](#project-init)
- [Older approach](#older-approach)

## New installation process 

- Switch user

```
sudo su <username>
```

- apt update - since you started with the vanilla import wsl

```
sudo apt update
```

### Curl

- Install curl

```
sudo apt install curl -y
```

### Install NVM

[Node version manager](https://github.com/nvm-sh/nvm) allows to manage node version easily

- Download and install
```
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.7/install.sh | bash
```

- Now this would return _command not found_
```
nvm 
```

- You need to **activate nvm**,

```
source ~/.bashrc
```

### Install Node & NPM

These steps would install the node and npm packages

- Install node LTS
```
nvm install --lts
```

- Install specific node version
```
nvm install 16.0.0
```

- Now to list all version installed
```
nvm ls
```
![](./resources/nvm-ls.png)

- So easily switch between the node version you want,
```
nvm use 20.11.1
```

- Check the corresponding node & npm versions,
```
node -v && npm -v
```

![](./resources/nvm-node-npm-version-check.png)


## Project init

### ACL (Check if needed)

- Install acl for providing permsissions,
```
sudo apt install acl
```

- Set permission to the required folder
```
setfacl -R -m u:<username>:rwx <foldername>
```

### Using webpack


- The following creats **package.json**
```
npm init
```

#### Webpack

- Now we need the following packages
![](./resources/webpack.png)

- The following is for webpack,
```
npm i webpack webpack-cli webpack-dev-server --save-dev
```

- Create _webpack.config.js_
```
npx generate-webpack-config
```
here the **npx** is the node package executor and it gets installed when node is installed, it helps to run the node package instead of running them from global installation

#### Typescript

- The following is for typescirpt
```
npm i typescript ts-loader --save-dev
```

- Init **tsconfig.ts**
```
npx tsc --init
```
here the **npx** is the node package executor and it gets installed when node is installed, it helps to run the node package instead of running them from global installation

- Add the following in the **webpack.config.ts**
```
const path = require('path');

module.exports = {
  entry: './src/index.ts',
  module: {
    rules: [
      {
        test: /\.ts?$/,
        use: 'ts-loader',
        exclude: /node_modules/,
      },
    ],
  },
  resolve: {
    extensions: ['.tsx', '.ts', '.js'],
  },
  output: {
    filename: 'main.bundle.js',
    path: path.resolve(__dirname, 'dist'),
  },
  devServer: {
    static: path.join(__dirname, "dist"),
    compress: true,
    port: 4000,
  },
};
```

- Update the scripts in package.json
```
  "scripts": {
    "start": "webpack-dev-server --mode development",
    "build": "webpack --mode production"
  }
```

#### Pack html along with main.js 

- HtmlWebpackPlugin allows webpack to generate a standard HTML page that will serve the generated bundle files

```
npm install html-webpack-plugin --save-dev
```

- Add this to webpack.config.js
```
const HtmlWebpackPlugin = require("html-webpack-plugin");
```

- Add the following to the **webpack.config.ts**
```
  plugins: [
    new HtmlWebpackPlugin({
        title: 'main index html', 
        template: 'src/index.html' }) 
   ],
```

#### Deploy - Github pages

- In order to deploy to github pages,
```
npm i gh-pages --save-dev
```

- Add the **deploy** script to existing script in **package.json**
```
  "scripts": {
    "start": "webpack-dev-server --mode development",
    "build": "webpack --mode production",
    "deploy": "gh-pages -d dist"
  },
```

The following is required in the **.github/workflow** - you can also use *node.js* pacakge from **github actions**
```
# This workflow will do a clean installation of node dependencies, cache/restore them, build the source code and run tests across different versions of node
# For more information see: https://docs.github.com/en/actions/automating-builds-and-tests/building-and-testing-nodejs

name: Node.js CI

on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

jobs:
  build:

    runs-on: ubuntu-latest

    strategy:
      matrix:
        node-version: [20.x]
        # See supported Node.js release schedule at https://nodejs.org/en/about/releases/

    steps:
    - uses: actions/checkout@v3
    - name: Use Node.js ${{ matrix.node-version }}
      uses: actions/setup-node@v3
      with:
        node-version: ${{ matrix.node-version }}
        cache: 'npm'
    - run: npm ci
    - run: npm run build --if-present
    # - run: npm test
  
    - name: Deploy with gh-pages
      run: |
        git remote set-url origin https://git:${GITHUB_TOKEN}@github.com/${GITHUB_REPOSITORY}.git
        npm run deploy -- -u "github-actions-bot <support+actions@github.com>"
      env:
        GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
```

#### Reference

- [Typescript with Webpack](https://medium.com/jspoint/integrating-typescript-with-webpack-4534e840a02b)
- [Typescript html project](https://blog.logrocket.com/using-webpack-typescript/)


### Using snowpack

```
npm init
```

- Webpack for smaller projects
```
npm i snowpack --save-dev
```

- Snowpack plugin for typescript & typescript
```
npm i @snowpack/plugin-typescript typescript --save-dev
```

- Init tsconfig.json
```
npx tsc --init
```
the above will create the JSON file

- Update scripts in package.json
```
"scripts" : {
    "start": "snowpack dev",
    "build": "snowpack build"
}
```

- Add the **app.ts** file into the src directory
```
console.log('connected');
```

- Import the script js file, even though we just have created the _app.ts_ file
```
<script src="src/app.js"></script>
```

- Start the application
```
npm run start
```

-- Build the application
```
npm run build
```


https://www.youtube.com/watch?v=6gB6HGxwjU0

## Older approach

### Old node installation

- install nodejs,

```
sudo apt install nodejs
```

- once installed, check version

```
node -v
```
shows, Welcome to Node.js v12.22.9.

> **NOTE** This is a older version the current version is **21.6.2**

### Old NPM installation

- install npm
```
sudo apt install npm
```

- check version
```
npm -v
```
shows, npm version 8.5.1
