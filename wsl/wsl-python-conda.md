
# Anaconda

This contains the instructions for running anacodna in __wsl2__.

- [Installation](#installation)
- [Verification](#verification)
- [Installing packages](#installing-packages)

## Installation

> **Note:** Make sure to be in as **a user instead of root**

Run in batch mode to avoid hitting yes to license manually,

```
bash Anaconda3-2023.09-0-Linux-x86_64.sh  -b -p $HOME/anaconda3
```

Default conda env

```
source /home/suriya/anaconda3/bin/activate
```

Init conda env
```
conda init
```

## Verification

Version
```
conda --version
```

Deactivate 
```
conda deactivate
```

Activate 
```
conda activate
```

### Show envs
this only show the default conda env right now
```
conda info --envs
```

### Create new conda env
```
conda create -n <env-name>
```
_eg. conda create -n first-env_

Activate it by,
```
conda activate first-env
```

### The base environment is not activated by default
conda config --set auto_activate_base False

## Installing packages

### Show packages

```
conda list
```

### Conda forge

Add conda-forge as the highest priority channel
```
conda config --add channels conda-forge
```

Activate strict channel priority (strict will be activated by default in conda 5.0).
```
conda config --set channel_priority strict
```

Alternatively without setting the above can pass the channel dynamically by,
```
conda install -c conda-forge jupyterlab=4.0.7 notebook=7.0.6
```

or 

```
conda create -c conda-forge --name py3.11 python=3.11
```

### Start installtion

List packages
```
conda list
```

The follwoing installs python
```
conda install python
```
(or)
to be sepcific,
```
conda install python=3.11
```

The fololowing install notebook and jupyterlab
```
conda install jupyter
```

### Code compeltion
This is for the autocomplete (Not working)
```
conda install -c conda-forge jupyter_contrib_nbextensions 
```

```
conda install -c conda-forge python-lsp-server r-languageserver
```
**Autocomplete is working by default from jupyterlab 4.11 could be set in code complete -> autocomplete**

Run the notebook,
```
jupyter notebook
```

Copy over the token and login - access from windows machine,
```
http:// http://127.0.0.1:8888/tree?token=fa59e49538d8bb924b16fd3184a006d94781e5f080c3a760
```

## Anaconda navigator

To launch naviagator (Does not work)
```
anaconda-navigator
```

```
apt-get install gedit

apt-get update && apt-get install libgl1
apt-get update && apt-get install ffmpeg libsm6 libxext6  -y
```
