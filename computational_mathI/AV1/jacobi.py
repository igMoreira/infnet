from copy import copy
from numpy import dot
from utils import is_diagonally_dominant

def stop_condition(x, x0, E):
    for i in range(len(x)):
        sub = x[i] - x0[i]
        if( E < abs(sub)):
            return False
    return True

def jacobi_method(A, x, B, maximum=1000, E=0.0001):
    """
        Solves systems of a diagonally dominant, square matrix
        Ax = B

    :param A: the A matrix containing the system
    :param x: the variables to discover
    :param B: the results of the system
    :param maximum:
    :return:
    """
    A = list(A); x = list(x); B = list(B)
    x0 = [ element for element in x]
    if(is_diagonally_dominant(A)):
        print('The matrix IS dominant !!!')
        iterations = 0
        for k in range(1, maximum):
            iterations=k
            for i in range(len(A)):
                print('Current solution : ', x)
                sum = 0.0
                for j in range(len(A)):
                    if( i != j):
                        sum = sum + (A[i][j] * x0[j])
                x[i] = (B[i] - sum) / (A[i][i])
            if( stop_condition(x, x0, E)): break
            x0 = copy(x)
        print('Solution : ', x)
        print('Error :', dot(A, x) - B)
        print('Number of iterations : ', iterations + 1)
    else:
        print('The matrix is NOT diagonally dominant !!!')
    return x