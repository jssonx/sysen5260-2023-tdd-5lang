import sys

import pandas as pd
from matmpy import mat_mpy

assert len(sys.argv) == 3, "Expecting arguments mat1.csv mat2.csv"
_, f_mat1, f_mat2 = sys.argv
mat_a = pd.read_csv(f_mat1, index_col=None, header=None)
mat_b = pd.read_csv(f_mat2, index_col=None, header=None)
ans = mat_mpy(mat_a, mat_b)
ans.to_csv(sys.stdout)
