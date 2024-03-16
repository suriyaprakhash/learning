# wsl-java

- [Basic](#basic)
- [Search for Open JDK](#search-for-open-jdk)
- [Install JDK](#install-jdk)
- [Switch versions](#switch-versions)

## Basic

- Switch user

```
sudo su <username>
```

- apt update - since you started with the vanilla import wsl

```
sudo apt update
```

## Search for Open JDK

```
apt search openjdk-21
```

Go with **headless** for servers and **jdk** for the ones with graphics and stuff _e.g JavaFX_


## Install jdk

```
sudo apt install openjdk-21-jdk
```

```
java -version
```

Check where Java is installed,
```
/usr/bin/java
```
The above is where the link if referenced, however it would be installed in the **usr/lib/jvm** folder

```
ls /usr/lib -la | grep jvm
```
(or)
```
ls /usr/lib/jvm --la
```

## Switch versions

- List all and switch alternatives installed,

```
sudo update-alternatives --config java
```

![](./resources/java-alternatives-switch.png)

- Check the version now,

```
java -version
```

