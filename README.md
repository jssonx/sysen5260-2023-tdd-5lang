# sysen5260-2023-tdd-5lang

1. Write a program in Java that reads two matrices from csv files and writes the
matrix-product to a different file.

a. You will be provided with:
i. Code to read the csv files into data structures and write data structures
into csv files.
ii. An empty matrix-multiply routine.
iii. A unit- test to confirm the matrix-multiply routine produces the correct
answer.
iv. A README.md with instructions to run the program and the tests.

b. You will:
i. Implement the matrix-multiply routine such that the unit-test passes.


2. Repeat step-1 in C, Go, JavaScript, and Python.


3. Prepare a written report to demonstrate your understanding:

a. Java
i. Is Java a compiled language or an interpreted language? Is Java statically
typed or dynamically typed?

ii. What is Maven? What are some of the things that we use Maven for in this
project?

iii. We use the Java keyword new to create new instances of our Matrix class
where we specify the size of the matrix. Can we change the size of a matrix
object after we create it? What do you see in the code that makes you think
so.


b. Go
i. Is Go a compiled language or an interpreted language? Is Go statically
typed or dynamically typed?

ii. Go comes with a full suite of build tools that help us create and configure
our modules. What do the following commands do?
1. go run .
2. go mod tidy
3. go build
4. go test

iii. What does the := operator do in Go?


c. JavaScript
i. Is JavaScript a compiled language or an interpreted language? Is JavaScript
statically typed or dynamically typed?
ii. In JavaScript is there any difference between an integer and a
floating-point number?

iii. What is npm? How did you use npm in your project?


d. C
i. Is C a compiled language or an interpreted language? Is C statically typed
or dynamically typed?

ii. In c/matmpy.c, line 16 we define a structure called t_matrix. This is a data
type with two attributes: matsize which is of type struct t_matsize,
and values which is of type double **.
What does the ** mean? Explain why we'd use this type to represent a
2-dimensional matrix of numbers.

iii. When constructing this assignment I needed it to work on Windows,
MacOS, and Linux hosts. One difference between these OSes is text-file
line-ending markers: On the Mac and Linux we use \n to mark the end of
the line. On Windows we use two characters: \r\n. This is a historical
holdover from DOS, Windows' original kernel.
Docker hides most of these host-OS details from the container, but because
we "mount" local folders inside the container (See lines 7 and 8 in the
c/docker-compose.yaml file.) I had to deal with the possibility of either
file-type. Where in matmpy.c can you find code that deals with this line-ending
difference? Can you find any other places in the c/ folder that deal with this
line-ending difference?

e. Python
i. Is Python a compiled language or an interpreted language? Is C statically
typed or dynamically typed?

ii. Unlike the Java project we didn't create a Matrix class. What class did we
use to represent our Matrix in the Python project? Why is this potentially
better than creating our own Matrix class?

iii. In our mat_mpy function definition, what does the -> symbol mean? Is it
required for our function to operate correctly? What is the advantage of
using this syntax?
