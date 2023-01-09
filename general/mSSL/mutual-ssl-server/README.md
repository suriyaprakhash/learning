# Mutual SSL Server

## Run

## Generating the same as pkcs12 (USE THIS)

### Keystore

keytool -genKey -alias suriya-server -keystore server-keystore.jks -storetype jks -keypass password -storepass password -keyalg RSA -deststoretype pkcs12

keytool -genKey -alias suriya-client -keystore client-keystore.jks -storetype jks -keypass password -storepass password -keyalg RSA -deststoretype pkcs12

### Cert

keytool -exportcert -alias suriya-server -keystore server-keystore.jks -file server-cert.cer -storepass password

keytool -exportcert -alias suriya-client -keystore client-keystore.jks -file client-cert.cer -storepass password

### TrustStore

keytool -importcert -keystore server-truststore.jks -file client-cert.cer -alias suriya-client -storepass password -trustcacerts -deststoretype pkcs12

keytool -importcert -keystore client-truststore.jks -file server-cert.cer -alias suriya-server -storepass password -trustcacerts -deststoretype pkcs12


## Generating certs (WORKING)

### Generate CA

CA Private Key generation

```sh
openssl genrsa -out ca-key.pem 4096
```

CA Public key from private key

```sh
openssl req -new -x509 -sha256 -days 1000 -key ca-key.pem -out ca-cert.pem
```

### Server certs

Server private key
```shell
openssl genrsa -out server-key.pem 4096
```

CSR for the server
```sh
openssl req -new -sha256 -key server-key.pem -out server-csr.csr
```

server-extfile.cnf
```
subjectAltName=DNS:*.suriya-server.com
```

```shell
openssl x509 -req -sha256 -days 1000 -in server-csr.csr -CA ca-cert.pem -CAkey ca-key.pem -out server-cert.pem -extfile server-extfile.cnf -CAcreateserial
```

### Client certs

Client Key
```shell
openssl genrsa -out client-key.pem 4096
```

```shell
openssl req -new -sha256 -key client-key.pem -out client-csr.csr
```

client-extfile.cnf
```
subjectAltName=DNS:*.suriya-client.com
```

```shell
openssl x509 -req -sha256 -days 1000 -in client-csr.csr -CA ca-cert.pem -CAkey ca-key.pem -out client-cert.pem -extfile client-extfile.cnf  -CAcreateserial
```

### Converting pem into JKS

#### Keystore

Combine the Cert and Key
```shell
cat ca-cert.pem server-cert.pem server-key.pem > server-combined.pem
```

convert it into .p12
```shell
openssl pkcs12 -export -in server-combined.pem -out server-cert.p12 -name suriya-server
```

Import .p12 to server-keystore.jks file
```shell
keytool -importkeystore -srckeystore server-cert.p12 -srcstoretype pkcs12 -destkeystore server-keystore.jks
```

Combine the Cert and Key
```shell
cat ca-cert.pem client-cert.pem client-key.pem > client-combined.pem
```

convert it into .p12
```shell
openssl pkcs12 -export -in client-combined.pem -out client-cert.p12 -name suriya-client
```

Import .p12 to client-keystore.jks file
```shell
keytool -importkeystore -srckeystore client-cert.p12 -srcstoretype pkcs12 -destkeystore client-keystore.jks
```

#### Cert and Truststore

```shell
keytool -exportcert -alias suriya-server -keystore server-keystore.jks -file server-cert.cer -storepass password
```

```shell
keytool -exportcert -alias suriya-client -keystore client-keystore.jks -file client-cert.cer -storepass password
```

```shell
cp ca-cert.pem ca-cert.cer
```

```shell
keytool -importcert -keystore server-truststore.jks -file ca-cert.cer -alias suriya-client -storepass password -trustcacerts -deststoretype pkcs12
```

```shell
keytool -importcert -keystore client-truststore.jks -file ca-cert.cer -alias suriya-server -storepass password -trustcacerts -deststoretype pkcs12
```

**NOTE** The above method does not check for the hostname as the hostname was not part of the openssl cert generation



### Importing CA into the java cacerts (NOT REQUIRED)

List cacerts
```cmd
keytool -list -keystore .\cacerts | select-string my-ca-cert
```
```shell
keytool -list -keystore cacerts | grep my-ca-cert
```
**changeit** is the password

import into cacert
```shell
keytool -importcert -alias my-ca-cert -file ca-cert.pem -keystore cacerts -storetype PKCS12
```

Host file changes,
192.168.1.206 my.suriya-client.com
192.168.1.206 my.suriya-server.com
