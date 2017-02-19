from sympy import *


def euler_method(yi, f, x, h):
    f_x_y = eval(f)
    return yi + f_x_y * h


def rk4(yi, f, x, h):
    k1 = eval(f)
    ym = yi + k1 * (h/2)

    x += (h/2)
    k2 = eval(f)
    ym = yi + k2 * (h/2)

    k3 = eval(f)
    ye = yi + k3 * h

    x -= (h/2); x+=h
    k4 = eval(f)

    slope = (k1 + 2*(k2 + k3) + k4) / 6
    return yi + slope *h

def rk2_ralston(yi, f, x, h):
    k1 = eval(f)
    ym = yi + (3/4)*k1*h

    x += ((3/4)*h)
    k2 = eval(f)

    slope = (((1/3)*k1) + ((2/3)*k2))
    return yi + slope *h


def execute_EDO_method(start, end, f, h, alg_method):
    x = Symbol('x')
    f_integral = str(integrate(f, x)) + ' + 1'

    stop = int((end - start) / h)
    x = start

    method_result = []
    real_result = []
    error_result = []
    x_coord = []

    for i in range(stop + 1):
        x = (i * h)
        x_coord.append(x)
        if i == 0:
            yi = eval(f_integral)
            method_result.append(yi)
            pass

        yi = alg_method(yi, f, x, h)

        method_result.append(yi)
        real_result.append(eval(f_integral))
        error_result.append(abs((real_result[i] - method_result[i]) / real_result[i]) * 100)

    print('x \t\t y_verdadeiro \t y_Method \t Error')
    for i in range(len(x_coord)):
        print('%.4f  %10.4f %10.4f %10.4f' % (x_coord[i], real_result[i], method_result[i], error_result[i]))


if __name__ == '__main__':
    print("-----------------Euler method-------------------")
    execute_EDO_method(0, 4, '-2*(x**3)  +  12*(x**2)  - 20*x  + 8.5', 0.5, euler_method)
    print()
    print("-----------------RK2----------------------------")
    execute_EDO_method(0, 4, '-2*(x**3)  +  12*(x**2)  - 20*x  + 8.5', 0.5, rk2_ralston)
    print()
    print("-----------------RK4----------------------------")
    execute_EDO_method(0, 4, '-2*(x**3)  +  12*(x**2)  - 20*x  + 8.5', 0.5, rk4)
