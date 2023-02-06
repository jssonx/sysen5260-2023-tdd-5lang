package main

import (
	"os"
	"testing"
)

func TestMatrixMpy(t *testing.T){
	mat_a := readMatrixOrPanic("/opt/data/mat_a.csv")
	mat_b := readMatrixOrPanic("/opt/data/mat_b.csv")
	// #mat_a.values[0][1] = 23
	expected := readMatrixOrPanic("/opt/data/mat_c.csv")
	mat_c,err := matrixMultiply(mat_a, mat_b)
	if err != nil {
		t.Errorf("Error: %v", err)
	}
	if cmpMatrix(mat_c, expected) == false{
		t.Errorf("mat_c=%v; expected=%v",mat_c, expected)
	}

}


func TestEndToEnd(t *testing.T){
	mat_a := readMatrixOrPanic("/opt/data/mat_a.csv")
	mat_b := readMatrixOrPanic("/opt/data/mat_b.csv")
	mat_c, err := matrixMultiply(mat_a, mat_b)
	panicOnError(err)
	tmp, err := os.Create("/tmp/actual.csv")
	panicOnError(err)
	writeMatrix(tmp, mat_c)
}