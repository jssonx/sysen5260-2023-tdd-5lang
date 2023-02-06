# Matrix Multiply in JavaScript
This project implements matrix-multiply in JavaScript.

# Usage
In order to use the `matmpy` program you must:

1. Start the container.
2. Run it.

# Start the Container
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


# Run
From the container shell prompt, run:

```
matmpy /opt/data/mat_a.csv /opt/data/mat_b.csv
```

This will execute the installed script: It will read the two files given, perform the multiply
operation, then return the result.


# Test
From the container shell prompt, run:

```
npm test
```

This will execute the Jest-tests.





# NPM Notes
I created this project with the following command:

```
npm init
```
...then answered some questions to populate the `package.json` file.

To install `jest` for unit-testing, I ran:

```
npm install --save-dev jest
```
* Note: `--save-dev` puts jest into the `dev` dependencies -- i.e. those only included in the development environment -- not in a production environment.


## Docker build
Our Dockerfile installs all libraries on build:

```
npm install -g .
```

This installs all dependencies specified in the `dependencies` section of the  `package.json` file then installs the `matmpy` command as a symlink to the `matmpy.js` script, as specified in the `bin` section of the `package.json` file.
