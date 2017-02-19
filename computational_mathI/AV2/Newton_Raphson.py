from sympy import *

def newton_raphson_method(F):
    x = Symbol('x')

    x0 = 1
    f = str(F)
    fprime = str(eval(f).diff(x))
    tolerance = 10**(-7)
    epsilon = 10**(-14)

    maxIterations = 20
    foundSolution = False

    for i in range(1, maxIterations):
        x=x0
        y=eval(f)
        yprime=eval(fprime)

        if (abs(yprime) < epsilon):
            break
        x1 = x0 - y/yprime
        if (abs(x1 - x0) <= (tolerance * abs(x1))):
            foundSolution = True
            break
        x0=x1
    if foundSolution:
        print('Solution : %s' % x1)
        print('Number of iterations : %s ' % i)
    else:
        print("Couldn't find a solution.")


f='(x**2) - 612'
newton_raphson_method(f)