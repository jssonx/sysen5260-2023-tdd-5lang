# Python matmpy
A Python program to multiply two matrices.

# Usage
To use the matmpy Python program you must:
1. Start the container.
2. Run the main.py python program.

## Starting the Container
To start the container, you need to run the following from this directory on your host machine:

```
docker-compose run shell
```

This will build and start a Linux container with Python installed as well as the following additional Python packages: 

* `pytest` -- used for running our tests.
* `pandas` -- provides CSV-reading and other routines.

Once the container builds and starts, you should be presented with a shell-prompt similar to this:

```
root@5e51423c8497:/opt/app#
```

This is the **container shell prompt** -- a bash shell running inside the container.


## Run
From the container shell prompt, run:

```
python -m main /opt/data/mat_a.csv /opt/data/mat_b.csv
```

This will load the main.py program and run it with the input-files as argumnts.

# Testing
To test your `mat_mpy` function, run the following from the container shell prompt:

```
pytest .
```

Pytest will search for files like `test_*.py`, collect the tests within those files, then run them. 

A PASSING test will show a test summary followed by something like
 `1 passed in 0.37s`

A FAILING test will show the Python traceback for the error.  This will usually include the line of code where the error occurred.
