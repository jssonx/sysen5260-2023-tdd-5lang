# MatrixMultiply in Java
This project implements matrix-multiply in Java.

# Usage
In order to use the MatrixMultiply program you must:

1. Start the container.
2. Build the sysen5260 JAR-file.
3. Run the MatrixMultiplyCommand program

## Starting the Container
To start the container, you need to run the following from this directory on your host machine:

```
docker-compose run shell
```

This will build and start a Linux container with all 
software required to build and run Go programs.

Once the container builds, you should be presented with a shell-prompt similar to this:

```
root@5e51423c8497:/opt/app#
```

This is the **container shell prompt** -- a bash shell running inside the container.

## Run Tests
To run the unit-tests, run the following command from the container shell prompt:

```
mvn test
```

## Build sysen5260 JAR file
To build the system5260 JAR-file, run the following command from the container shell prompt:

```
mvn clean compile assembly:single
```

This will instruct Maven to produce a file `target/sysen5260-1.0-SNAPSHOT-jar-with-dependencies.jar`. (See "Maven Notes" below for details.)


## Run the MatrixMultiplyCommand program
To run our program stored within the JAR-file, we run the following command from our container shell prompt:

```
java -cp target/sysen5260-1.0-SNAPSHOT-jar-with-dependencies.jar edu.cornell.sysen5260.MatrixMultiplyCommand /opt/data/mat_a.csv /opt/data/mat_b.csv
```

This should print the resulting matrix to the screen.


# Maven Notes
The typical output for languages like C or Go is an executable. Java is a bit different: You typically build a
collection of object-oriented classes and package them into a JAR-file.  This project depends on some external repositories like OpenCSV so we need to download these first, then compile our `.java`-files into `.class`-files using the `javac` command.  We also want to run our unit-tests, then when that all works, we want to build a JAR with all of our classes.  This is an awful lot to manage!  

This is where the Maven build system helps:  It automates these steps (and more) for us base on the `POM.xml` configuration
file. 

Here's the maven command I used to create this project:

```
mvn archetype:generate -DgroupId=edu.cornell.sysen5260 -DartifactId=sysen5260 -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```
...This created a default file-directory structure and `POM.xml`.

I edited this `POM.xml` a bit to support a few additional needs for this project:
* I added a dependency on the OpenCSV project -- an open-source CSV-reading/writing library;
* I added the "maven-assembly-plugin" to enable maven to produce a JAR that includes all dependencies.

References:
* https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

