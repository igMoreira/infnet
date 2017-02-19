from numpy import diag, array

def is_diagonally_dominant(A):
    """
        Verifies if a given matrix is diagonally dominant.
    :param A:The matrix to verify
    :return:
    """
    A = abs(array(A))
    diagonal = diag(A)
    return all( [ ( (diagonal[i]) >= (sum(A[i]) - diagonal[i]) ) for i in range(len(diagonal)) ] )