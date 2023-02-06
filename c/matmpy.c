#include <stdio.h>
#include <stdlib.h>

#define BAD_FORMAT -2
#define BAD_NUMBER -1
#define END_MATRIX 0
#define VAL 1
#define END_ROW 2


struct t_matsize {
    int nrows;
    int ncols;
};

struct t_matrix{
    struct t_matsize matsize;
    double **values;
};

struct t_matrix EMPTY_MATRIX = {
    (struct t_matsize){0,0},
    NULL
};

int next_matrix_symbol(FILE* fp, double *val){
    int n = fscanf(fp, "%lf", val);
    if(n != 1){
        return BAD_NUMBER;
    }
    char sep = fgetc(fp);
    if(sep == ','){
        return VAL;
    }
    if(sep == '\n'){
        return END_ROW;
    }
    if(sep == '\r'){
        sep = fgetc(fp);
        if(sep == '\n'){
            return END_ROW;
        }else{
            return BAD_FORMAT;
        }
    }
    if(sep == EOF){
        return END_MATRIX;
    }
    return BAD_FORMAT;
}

struct t_matsize read_matrix_dimensions(FILE *fp){
    struct t_matsize mat_size;
    mat_size.nrows = 0;
    mat_size.ncols = 0; 
    double val;
    int status;
    while( (status = next_matrix_symbol(fp, &val)) > 0){
        if((status == VAL) && (mat_size.nrows == 0)){
           mat_size.ncols++;
        }else if(status == END_ROW){
            mat_size.nrows++;
        }
    }
    mat_size.ncols++;
    return mat_size;
}

int read_matrix_values(FILE *fp, int rows, int cols, double **values){
    int row = 0;
    int col = 0; 
    double val;
    int status;
    while( (status = next_matrix_symbol(fp, &val)) > 0){
        if(status == VAL){
            values[row][col] = val;
            col++;
        }else if(status == END_ROW){
            values[row][col] = val;
            row++;
            col=0;
        }
    }
    return 0;
}

double **malloc_matrix(struct t_matsize matsize){
    double **matrix = malloc(matsize.nrows * sizeof(double*));
    for(int i=0; i<matsize.nrows; i++){
        matrix[i] = malloc(matsize.ncols *sizeof(double));
    }
    return matrix;
}

void free_matrix(struct t_matrix matrix){
    for(int i=0; i < matrix.matsize.nrows; i++){
        free(matrix.values[i]);
    }
    free(matrix.values);
    matrix.values = 0L;
}

struct t_matrix read_matrix(FILE* fp){
    struct t_matrix matrix;

    fseek(fp, 0, SEEK_SET);
    matrix.matsize = read_matrix_dimensions(fp);
    fseek(fp, 0, SEEK_SET);
    matrix.values = malloc_matrix(matrix.matsize);
    read_matrix_values(fp, matrix.matsize.nrows, matrix.matsize.ncols, matrix.values);
    return matrix;
}

void print_matrix(struct t_matrix matrix){
    for(int row=0; row < matrix.matsize.nrows; row++){
        for(int col=0; col < matrix.matsize.ncols; col++){
            printf("%f", matrix.values[row][col]);
            if(col<(matrix.matsize.ncols-1)){
                printf(", ");
            }
        }
        printf("\n");
    }
}

struct t_matrix multiply(struct t_matrix a, struct t_matrix b){
    struct t_matrix c;
    c.matsize.nrows = a.matsize.nrows;
    c.matsize.ncols = b.matsize.ncols;
    c.values = malloc_matrix(c.matsize);
    // TODO: Implement Matrix Multiply
    return c;
}

struct t_matrix load_matrix(char *filename){
    FILE* fp = fopen(filename, "r");
    if(fp == NULL){
        return EMPTY_MATRIX;
    }
    struct t_matrix matrix_a = read_matrix(fp);
    fclose(fp);
    return matrix_a;
}

int main(int argc, char **argv){
    if (argc != 3){
        printf("Usage: %s <path-to-mat-a.csv> <path-to-mat-b.csv>\n");
        return -1;
    }
    struct t_matrix a = load_matrix(argv[1]);
    struct t_matrix b = load_matrix(argv[2]);
    if ( (a.matsize.nrows == 0) || (b.matsize.nrows == 0)){
        printf("Couldn't open matrix files.\n");
        return -1;
    }
    struct t_matrix c = multiply(a, b);

    print_matrix(c);
    free_matrix(a);
    free_matrix(b);
    free_matrix(c);
}