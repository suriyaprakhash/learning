# Creating multiple wsl environment using the same distro

- [Install](#install)
- [List](#show-list)
- [Export](#export)
- [Import](#import)
- [Start a distro](#start-a-distro)
- [Set default instance](#set-default-instance)
- [Shutdown vs Terminate vs Unregister](#shutdown-vs-terminate-vs-unregister)

The following commands are for **wsl2**

## Install

```powershell
wsl --install ubuntu
```

## Show List

```
wsl -l -v
```

## Export

This exports the above fresh distro files into the given folder as **a tar** file, so we can make 
multiple copies from it by [importing](#import) it.

```
wsl --export ubuntu C:\Users\suriy\main\wsl\ubuntu\ubuntu.tar
```

## Import

Spin the new instance, here **ubuntu-python** from the [exported fresh ubuntu tar file](#export)

```
wsl --import ubuntu-python C:\Users\suriy\main\wsl\python C:\Users\suriy\main\wsl\ubuntu\ubuntu.tar
```

## Start a distro

- Via command line
```
wsl -d ubuntu-python
```
- Using **power shell**

## Set default instance

```
wsl --set-default ubuntu-default
```

## Shutdown vs Terminate vs Unregister


**Shutdown** will shutdown all the currently running instances
```
wsl --shutdown
```

**Terminate** will shutdown the specified instance
```
wsl --terminate ubuntu-default
```

**Unregister** will wipe all the data for the specified instance
```
wsl --unregister ubuntu-default
```