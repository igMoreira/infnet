from numpy import dot
from utils import is_diagonally_dominant


def stop_condition(A, x, B, E):
    error = dot(A,x) - B
    for element in abs(error):
        if E < element:
            return False
    return True


def gauss_seidel(A, x, B, maximum=5, E=0.00001):
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
    if(is_diagonally_dominant(A)):
        print('The matrix IS dominant !!!')
        iterations = 0
        for k in range(1, maximum):
            iterations = k
            for i in range(len(A)):
                print('Current solution : ', x)
                sum = 0.0
                for j in range(len(A)):
                    if( i != j):
                        sum = sum + (A[i][j] * x[j])
                x[i] = (B[i] - sum) / (A[i][i])
            if( stop_condition(A,x,B,E)): break
        print('Solution : ', x)
        print('Error :', dot(A, x) - B)
        print('Number of iterations : ', iterations + 1)
    else:
        print('The matrix is NOT diagonally dominant !!!')
    return x