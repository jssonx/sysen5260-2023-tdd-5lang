package edu.cornell.sysen5260;

import javax.management.RuntimeErrorException;

public class MatrixMultiplyCommand {
    public static void main(String[] args) throws Exception{
        if(args.length != 2){
            throw new IllegalArgumentException("Expected 2 parameters: filename-matrix-a.csv filename-matrix-b.csv");
        }
        Matrix matA = Matrix.readFromCsvFile(args[0]);
        Matrix matB = Matrix.readFromCsvFile(args[1]);
        Matrix result = matA.multiply(matB);    
        Matrix.writeCsvToStdout(result);
    }
}
