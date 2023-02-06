import pandas as pd
from pandas.testing import assert_frame_equal
import matmpy

def test_matmpy():
    mat_a = pd.read_csv('/opt/data/mat_a.csv', index_col=None, header=None)
    mat_b = pd.read_csv('/opt/data/mat_b.csv', index_col=None, header=None)    
    expected = pd.read_csv('/opt/data/mat_c.csv', index_col=None, header=None)    

    actual = matmpy.mat_mpy(mat_a, mat_b)
    assert_frame_equal(expected, actual)
    