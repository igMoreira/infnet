from AV1.trapezoidal_method import trapm

F = '0.2 + 25*x - 200*x**2 + 675*x**3 - 900*x**4 + 400*x**5'

def romberg(f, a, b, MAX, es):
    I = [[0 for column in range(10)] for line in range(10)]

    n = 1
    I[1][1] = trapm(f, n, a, b)
    iter = 0
    while True:
        iter += 1
        n = 2**iter
        I[iter+ 1][1] = trapm(f, n, a, b)
        for k in range(2, iter+2, 1):
            j = 2 + iter - k
            I[j][k] = ( (4**(k-1)) * I[j+1][k-1] - I[j][k-1] ) /  ( (4**(k-1)) -1 )
        ea = abs( (I[1][iter+1] - I[2][iter] ) / I[1][iter+1] ) * 100
        print("Error: %f" %ea)
        if (iter >= MAX) or (ea <= es): break
    Romberg = I
    return Romberg

def execute_romberg(f, a, b, MAX, es):
    I = romberg(f, a, b, MAX, es)
    print("\nResults:")
    aux = [[column for column in line if column != 0] for line in I]
    [ print(aux[i]) for i in range(0, len(aux)) if len(aux[i]) > 0]

# execute_romberg(F,0.0,0.8, 100, 10**-5000)