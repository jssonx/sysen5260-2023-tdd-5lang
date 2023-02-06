#!/usr/bin/env node
var matrix = require("./matrix.js");

if (process.argv.length == 4){
    [nodeExe, script, fileA, fileB] = process.argv;

    let matrixA = matrix.readMatrix(fileA);
    let matrixB = matrix.readMatrix(fileB);
    let result = matrix.multiply(matrixA, matrixB);
    console.log(matrix.matrixToString(result));
}else{
    console.log("Expected 2 arguments but got " + process.argv.length);
    console.log("Usage: matmpy fileA.csv fileB.csv");
}