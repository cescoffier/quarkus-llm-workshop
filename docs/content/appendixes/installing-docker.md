# Installing Docker or Podman

Docker is a set of utilities that use OS-level virtualization to deliver software in packages called containers.
Containers are isolated from one another and bundle their software, libraries, and configuration files; they can communicate with each other through well-defined channels.

## Installing Docker

Quarkus uses Testcontainers, and therefore also Docker, to ease the management of different technical services (database, monitoring...) during development. Our workshop also uses Docker to manage these services in a production-style deployment. So for this, we need to install `docker` and `docker compose`. Installation instructions are available on the following page:

- Mac OS X - [Docker for Mac](https://docs.docker.com/docker-for-mac/install/) (version 20+)
- Windows - [Docker for Windows](https://docs.docker.com/docker-for-windows/install/) (version 20+)
- CentOS - [Docker CE for CentOS](https://docs.docker.com/install/linux/docker-ce/centos/)
- Debian - [Docker CE for Debian](https://docs.docker.com/install/linux/docker-ce/debian/)
- Fedora - [Docker CE for Fedora](https://docs.docker.com/install/linux/docker-ce/fedora/)
- Ubuntu - [Docker CE for Ubuntu](https://docs.docker.com/install/linux/docker-ce/ubuntu/)

On Linux, don't forget the post-execution steps described on [Linux Post-installation Steps](https://docs.docker.com/install/linux/linux-postinstall/).

NOTE: If you do not have a Docker license, you might prefer `podman` ([Podman](https://podman.io/)) instead of `docker`. To install `podman` and `podman-compose`, please follow the instructions at [Quarkus Podman Guide](https://quarkus.io/guides/podman). Do not forget the extra steps to configure the Testcontainers library. As a convenience, you can even alias `docker` to `podman`.

## Checking for Docker Installation

Once installed, check that both `docker` and `docker compose` are available in your `PATH`. For that, execute the following commands:

```shell
$ docker version
```

You should see something like this:

```
Docker version 20.10.8, build 3967b7d
Cloud integration: v1.0.24
Version:           20.10.14
API version:       1.41
```

Then, check the Docker Compose version:

```shell
$ docker compose version
```

Docker Compose being a separate utility, you should get a different version than Docker itself:

```
Docker Compose version v2.5.0
```

Finally, run your first container as follows:

```
$ docker run hello-world
```

For the first time, this will download the _hello-world_ container image from the Docker Hub and run it. 
You should get something like this:

```text
Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
1. The Docker client contacted the Docker daemon.
2. The Docker daemon pulled the "hello-world" image from the Docker Hub. (amd64)
3. The Docker daemon created a new container from that image which runs the executable that produces the output you are currently reading.
4. The Docker daemon streamed that output to the Docker client, which sent it to your terminal.
```

To try something more ambitious, you can run an Ubuntu container with:

```shell
$ docker run -it ubuntu bash
```

## Some Docker Commands

Docker is a command-line utility where you can use several parameters and options to start/stop a container. 
You invoke docker with zero, one, or several command-line options with the container or image ID you want to work with. 
Docker comes with several options that are described in the documentation if you need more help[^1].
To get some help on the commands and options, you can type, use the following command:

```shell
$ docker help
```

Here are some commands that you will be using to start/stop containers in this workshop.

* `docker container ls`: Lists containers.
* `docker container start CONTAINER`: Starts one or more stopped containers.
* `docker compose -f docker-compose.yaml up -d`: Starts all containers defined in a Docker Compose file.
* `docker compose -f docker-compose.yaml down`: Stops all containers defined in a Docker Compose file.

## References

[^1]: [Docker commands](https://docs.docker.com/engine/reference/commandline/docker/)
