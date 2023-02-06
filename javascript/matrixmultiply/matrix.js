
// import { parse } from '@vanillaes/csv'
const fs = require('fs');
const csvParse = require('csv-parse/sync');
const csvGenerate = require('csv-generate/sync');

// import { parse } from '@vanillaes/csv'


function matmpy(a, b) {
    return a+b;
}

function readMatrix(filename){
    let rows = csvParse.parse(fs.readFileSync(filename));
    for(i=0;i<rows.length;i++){
        let row = rows[i];
        for(j=0;j<row.length;j++){
            rows[i][j] = parseFloat(rows[i][j]);
        }
    }
    return rows;
}

function matrixToString(matrix){
    return matrix.map(row => row.map(value => value.toString()).join(',')).join('\n');
}

function writeMatrix(matrix, filename){
    fs.writeFileSync(filename, matrixToString(matrix));
}


function multiply(matrixA, matrixB){
    let nrows = matrixA.length;
    let ncols = matrixB[0].length;
    let blen = matrixB.length;
    let result = new Array(nrows);
    // TODO: Implement Matrix Multiply
    return result;
}


/* References:
    * [fs / Node's filesystem lib](https://nodejs.org/dist/latest-v6.x/docs/api/fs.html)
    * [CSV Usage](https://github.com/adaltas/node-csv)
*/

module.exports = {matmpy, readMatrix, writeMatrix, multiply, matrixToString};