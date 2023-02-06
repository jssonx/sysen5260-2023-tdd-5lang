package edu.cornell.sysen5260;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.ListIterator;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import com.opencsv.exceptions.CsvException;


/**
 * A Matrix of linear-algebra-vareity.
 */
public class Matrix{
    public final int nRows;
    public final int nCols;
    private double[][] values;

    /**
     * Constructor. 
     * 
     * @param nRows -- total number of rows in matrix.
     * @param nCols -- total number of columns in matrix.
     */
    public Matrix(int nRows, int nCols){
        this.nRows = nRows;
        this.nCols = nCols;
        this.values = new double[nRows][nCols];
    }

    /**
     * Read a Matrix from a CSV-file.
     * 
     * @param fileName -- filename of CSV-file to read.
     * @return -- Matrix object representation of CSV-file.
     * @throws IOException
     * @throws CsvException
     */
    public static Matrix readFromCsvFile(String fileName) throws IOException, CsvException{
        CSVReader reader = new CSVReaderBuilder(new FileReader(fileName)).build();
        List<String[]> rows = reader.readAll();
        Matrix m = new Matrix(rows.size(), rows.get(0).length);
        for(ListIterator<String[]> it = rows.listIterator(); it.hasNext();){
            int rowNum = it.nextIndex();
            String[] rowValues = it.next();
            for(int colNum=0; colNum < rowValues.length; colNum++){
                double value = Double.parseDouble(rowValues[colNum]);
                m.set(rowNum, colNum, value);
            }
        }
        return m;
    }

    /**
     * Write a Matrix object as CSV to file.
     * 
     * @param m -- Matrix to write.
     * @param fileName -- Filename of destination file.
     * @throws IOException
     */
    public static void writeToCsvFile(final Matrix m, String fileName) throws IOException{
        Writer fileWriter = new FileWriter(fileName);
        writeCsvToGenericWriter(m, fileWriter);
    }


    /**
     * Write Matrix object as CSV to stdout.
     * @param m
     * @throws IOException
     */
    public static void writeCsvToStdout(final Matrix m) throws IOException{
        Writer stdoutWriter = new OutputStreamWriter(System.out);
        writeCsvToGenericWriter(m, stdoutWriter);
    }

    /**
     * Resuable logic to write receiver to CSV given any writer as the final destination.
     * @param m
     * @param writer
     * @throws IOException
     */
    private static void writeCsvToGenericWriter(final Matrix m, Writer writer) throws IOException{
        ICSVWriter  csvWriter = new CSVWriterBuilder(writer).build();
        for(int rowNum=0; rowNum < m.nRows; rowNum++){
            String[] nextLine = new String[m.nCols];
            for(int colNum=0; colNum < m.nCols; colNum++){
                nextLine[colNum] = Double.toString(m.get(rowNum, colNum));
            }
            csvWriter.writeNext(nextLine);
        }
        csvWriter.close();
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null){
            return false;
        }
        if (obj.getClass() != this.getClass()){
            return false;
        }
        final Matrix other = (Matrix) obj;
        if (other.nCols != this.nCols){
            return false;
        }
        if (other.nRows != this.nRows){
            return false;
        }
        for(int i=0;i<this.nRows;i++){
            for(int j=0;j<this.nCols;j++){
                if(this.get(i, j) != other.get(i, j)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Set a matrix-element value.
     * @param row -- A row-number on [0, nRows)
     * @param col -- A column-number on [0, nCols)
     * @param value -- The value to set that position to.
     */
    public void set(int row, int col, double value){
        this.values[row][col] = value;
    }

    /**
     * Get a matrix-element value.
     * 
     * @param row -- A row-number on [0, nRows)
     * @param col -- A column-number on [0, nCols)
     * @return The matrix element value.
     */
    public double get(int row, int col){
        return this.values[row][col];
    }

    /**
     * Multiply this matrix (on the left) by the other matrix (on the right).
     * 
     * @param other -- the other matrix.
     * @return -- result matrix: this * other.
     */
    public Matrix multiply(final Matrix other){
        Matrix result = new Matrix(this.nRows, other.nCols);
        // TODO: Implement Matrix-Multiply 
        return result;
    }
    
}