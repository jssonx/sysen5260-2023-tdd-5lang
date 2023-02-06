# matrixmultiply in Go
This project implements matrix-multiply in Go.

# Usage
In order use the `matrixmultiply` program you must:

1. Start the container.
2. Build the matrixmultiply program.
3. Run it.

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

## Build
From the container shell prompt, run:

```
go build
```

This builds the `matrixmultiply` executable from source-code.

## Run
From the container shell prompt, run:

```
./matrixmultiply /opt/data/mat_a.csv /opt/data/mat_b.csv
```

## Testing
To run our unit-test in Go, run the following from the container shell prompt:

```
go test
```

A successful unit-test will produce output similar to:

```
PASS
ok      sysen5260/matrixmultiply        0.007s
```
