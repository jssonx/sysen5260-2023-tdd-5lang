package main

import (
	"encoding/csv"
	"errors"
	"fmt"
	"io"
	"os"
	"strings"
	"strconv"

)

type matrix struct {
	nrows int
	ncols int
	values [][]float64
}

func getMatrixSize(fp *os.File) ([2]int, error) {
	r := csv.NewReader(fp)
	nrows := 0
	ncols := 0
	for {
		record, err := r.Read()
		if err == io.EOF {
			break
		}
		if err != nil{
			return [2]int{0,0}, err
		}
		nrows += 1
		if len(record) > ncols {
			ncols = len(record)
		}
	}
	ans := [2]int{nrows, ncols}
	return ans, nil
}

func writeMatrix(fp *os.File, mat matrix){
	for row:=0; row < mat.nrows; row++ {
		for col:=0; col < mat.ncols; col++ {
			fmt.Fprintf(fp, "%f", mat.values[row][col])
			if col < mat.ncols - 1 {
				fmt.Fprintf(fp, ",")
			}
		}
		fmt.Fprintln(fp, "")
	}
	
}

func makeMatrix(nrows int, ncols int) matrix{
	var ans matrix
	ans.nrows = nrows
	ans.ncols = ncols
	ans.values = make([][]float64, ans.nrows)
	for i:=0;i<ans.nrows;i++{
		ans.values[i] = make([]float64, ans.ncols)
	}
	return ans
}


func cmpMatrix(mat_a matrix, mat_b matrix) bool{
	if mat_a.nrows != mat_b.nrows{
		return false
	}
	if mat_a.ncols != mat_b.ncols{
		return false
	}
	for i:=0;i<mat_a.nrows;i++{
		for j:=0;j<mat_a.ncols;j++{
			if mat_a.values[i][j] != mat_b.values[i][j]{
				return false
			}
		}
	}
	return true
}

func readMatrix(fp *os.File) (matrix, error){
	var ans matrix
	matSize, err := getMatrixSize(fp)
	if err != nil {
		return ans, err
	}
	ans.nrows = matSize[0]
	ans.ncols = matSize[1]

	ans.values = make([][]float64, ans.nrows)
	for i:=0;i<ans.nrows;i++{
		ans.values[i] = make([]float64, ans.ncols)
	}

	fp.Seek(0, io.SeekStart)
	csvReader := csv.NewReader(fp)
	for row:=0; row < ans.nrows; row++ {
		line, err := csvReader.Read()
		if err != nil {
			return ans, err
		}
		for col, item := range line {
			cleanItem := strings.TrimSpace(item)
			floatItem, err := strconv.ParseFloat(cleanItem, 64)
			if err != nil {
				return ans, err
			}
			ans.values[row][col] = floatItem
		}
	}
	
	return ans, nil
}

func matrixMultiply(mat_a matrix, mat_b matrix) (matrix, error) {
	ans := makeMatrix(mat_a.nrows, mat_b.ncols)
	if mat_a.ncols != mat_b.nrows{
		return ans, errors.New("matrixMultiply: mat_a.ncols must == mat_b.nrows.")
	}
	// TODO: Implement matrix multiply 
	return ans, nil
}


func panicOnError(err error){
	if err != nil {
		panic(err)
	}
}


func readMatrixOrPanic(path string) matrix{
	f, err := os.Open(path)
	panicOnError(err)
	mat, err := readMatrix(f)
	panicOnError(err)
	return mat
}


func main(){
	if len(os.Args) != 3{
		fmt.Println("Expected usage:")
		fmt.Println("matrixmultiply file-a.csv file-b.csv")
		fmt.Println("")
		panic(errors.New("Wrong number of arguments."))
	}
	mat_a := readMatrixOrPanic(os.Args[1])
	mat_b := readMatrixOrPanic(os.Args[2])
	mat_c, err := matrixMultiply(mat_a, mat_b)
	panicOnError(err)
	writeMatrix(os.Stdout, mat_c)
}

