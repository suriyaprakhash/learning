# Linux Network commands

## Contents

- [Host information](#host-information)

## Host information

Check *hostname*,

```
hostname
```

Check FQDN-Fully qualified domain name

```
hostname -f
```

Change hostname
```
hostnamectl set-hostname kdc.kerberos.com
```

**Note** if hostname not found, then use
```
apt-get install systemd-services
```