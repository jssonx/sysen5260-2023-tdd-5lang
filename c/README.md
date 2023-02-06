# MatMpy in C
This project implements matrix-multiply in C.

# Usage
In order to use the `matmpy` program you must:

1. Start the container.
2. Compile the matmpy program.
3. Run it.


## Starting the Container
To start the container, you need to run the following from this directory on your host machine:

```
docker-compose run shell
```

This will build and start a Linux container with all software required to compile and run C programs.

Once the container builds, you should be presented with a shell-prompt similar to this:

```
root@5e51423c8497:/opt/app#
```

This is the **container shell prompt** -- a bash shell running inside the container.


## Build
From the container shell prompt, run:

```
make matmpy
```

This will compile the `matmpy.c` program into an executable `matmpy`.


## Run
From the container shell prompt, run:

```
./matmpy /opt/data/mat_a.csv /opt/data/mat_b.csv
```

This will run the `matmpy` program, passing the two CSV matrix input files as arguments. 
Once the `multiply` function is correctly implemented, this will print the resulting
matrix to the screen in CSV format.


# Testing
We provide a simple end-to-end test to test your program is running correctly.  

You can run this test from the container shell prompt as follows:

```
make test
```

A PASSING test will print:

```
PASS
```


A FAILING test will print either a runtime error indicating a problem with your build or a 
diff error indicating a problem with your results.

Until you correctly implement the `multiply` function, you should expect this test to fail.

* **Note:** This is a pretty crude way to do testing.  To do more serious TDD in C we should
    use a testing-framework like 
    [one of these](https://en.wikipedia.org/wiki/List_of_unit_testing_frameworks#C)