# Mutual SSL Server

## Run

## Generating certs - with keytool JKS

keytool -genKey -alias suriya-server -keystore server-keystore.jks -storetype jks -keypass password -storepass password -keyalg RSA
keytool -genKey -alias suriya-client -keystore client-keystore.jks -storetype jks -keypass password -storepass password -keyalg RSA

keytool -exportcert -alias suriya-server -keystore server-keystore.jks -file server-cert.cer -storepass password
keytool -exportcert -alias suriya-client -keystore client-keystore.jks -file client-cert.cer -storepass password

keytool -importcert -keystore server-truststore.jks -file client-cert.cer -alias suriya-client -storepass password -trustcacerts
keytool -importcert -keystore client-truststore.jks -file server-cert.cer -alias suriya-server -storepass password -trustcacerts

### To convert to pkcs12 from jks proprietary format

keytool -importkeystore -srckeystore server-keystore.jks -destkeystore server-keystore.jks -deststoretype pkcs12
keytool -importkeystore -srckeystore client-keystore.jks -destkeystore client-keystore.jks -deststoretype pkcs12

keytool -importkeystore -srckeystore server-keystore.jks -destkeystore server-keystore.jks -deststoretype pkcs12
keytool -importkeystore -srckeystore client-keystore.jks -destkeystore client-keystore.jks -deststoretype pkcs12



## Generating certs - Incorrect with Openssl

### Server

openssl genrsa -out server-key.pem 4096
openssl req -new -x509 -sha256 -days 1000 -key server-key.pem -out server-cert.pem

keytool -importcert -keystore server-truststore.jks -file client-cert.pem -alias suriya-client -storepass password -trustcacerts


keytool -list -keystore server-truststore.jks

### Client

openssl genrsa -out client-key.pem 4096
openssl req -new -x509 -sha256 -days 1000 -key client-key.pem -out client-cert.pem

keytool -importcert -keystore client-truststore.jks -file server-cert.pem -alias suriya-server -storepass password -trustcacerts

keytool -list -keystore client-truststore.jks

## Generating certs - Incorrect with CA

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

### Importing CA into the java cacerts

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