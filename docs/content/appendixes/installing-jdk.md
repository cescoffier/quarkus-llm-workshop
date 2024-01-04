# Installing Java {{ attributes.versions.java }}

Essential for the development and execution of this workshop is the _Java Development Kit_ (JDK).[^1]
The JDK includes several tools such as a compiler (`javac`), a virtual machine, a documentation generator (`JavaDoc`), monitoring tools (Visual VM), and so on[^2]. The code in this workshop uses JDK {jdk-version}.

## Installing the JDK

To install the JDK {{ attributes.versions.java }}, follows the instructions from [Adoptium Installation](https://adoptium.net/installation.html) to download and install the JDK for your platform.

### For Mac OS X

There is also an easier way to download and install Java if you are on Mac OS X: using SDKMAN! ([SDKMAN!](https://sdkman.io/)) is a tool for managing parallel versions of multiple Software Development Kits (SDK) on most Unix-based systems. It provides a convenient Command Line Interface (CLI) and API for installing, switching, removing, and listing _Candidates_. Developers often need to manage parallel versions of different builds of SDKs in their environment and switch from one to another. Manually setting the `PATH` variable can become quickly painful. That's when SDKMAN! can help you.

#### Installing SDKMAN!

Installing SDKMAN! is easy. On Bash and ZSH shells simply open a new terminal and enter:

```shell
$ curl -s "https://get.sdkman.io" | bash
```

Follow the instructions on-screen to complete installation.

Listing Java Versions
To install Java, we need to list the available versions on SDKMAN! using the list command. The result is a table of entries grouped by the vendor and sorted by version:

```shell
$ sdk list java
```

If you have any Java candidate installed, you should see installed in the Status column. 
If you don't have any Java candidate installed, use SDKMAN! to install one or several.

#### Installing Java {{ attributes.versions.java }}
There are several different vendors of Java, and each vendor has its own distribution. 
Most of these distributions are available on SDKMAN! and can easily be installed. 
Let's install Temurin.

To install Temurin {{ attributes.versions.java }}, we copy its identifier ({{ attributes.versions.java }}-tem), which is the version from the table, and we add it as an argument in the install command:

```shell
$ sdk install java {{ attributes.versions.java }}-tem

Downloading: java {{ attributes.versions.java }}-tem
Repackaging Java {{ attributes.versions.java }}-tem...
Installing: java {{ attributes.versions.java }}-tem
Done installing!
Do you want java {{ attributes.versions.java }}-tem to be set as default? (Y/n):
```

### For Linux
For Linux distributions, there are also packaged Java installations.

For rpm-based systems (e.g., Fedora):

```shell
dnf install java-{{ attributes.versions.java }}-openjdk
```

For Debian-based distributions (e.g., Ubuntu):

```shell
$ apt-get install openjdk-{{ attributes.versions.java }}-jdk
```

## Checking for Java Installation
Once the installation is complete, it is necessary to set the `JAVA_HOME` variable and the `$JAVA_HOME/bin` directory to the `PATH` environment variable. 
Check that your system recognizes Java by entering `java -version` and the Java compiler with `javac -version`.

```shell
$ java -version
openjdk version "17.0.5" 2022-10-18
$ javac -version
javac 17.0.5
```


