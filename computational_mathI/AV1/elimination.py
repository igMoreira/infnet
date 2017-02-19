def gauss_elimination(A, x, B):
    """
        time complexity = O(n^3)
    :return:
    """
    A = list(A); x = list(x); B = list(B)
    n = len(B)
    for k in range(n-1):
        for i in range(k+1, n):
            m = A[i][k] / A[k][k]
            for j in range(n):
                A[i][j] = A[i][j] - (m * A[k][j])
            B[i] = B[i] - (m * B[k])
    x[n-1] = B[n - 1] / A[n - 1][n - 1]
    iterations = 0
    for k in range(n-2, -1, -1):
        iterations = k
        x[k] = B[k]
        print('Current solution : ', x)
        for i in range(k+1, n):
            x[k] = x[k] - A[k][i] * x[i]
        x[k] = x[k] / A[k][k]
    print('Solution : ', x)
    print('Number of iterations : ', iterations + 1)
    return x