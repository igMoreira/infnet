# f(x) = 0.2 + 25*x - 200*x**2 + 675*x**3 - 900*x**4 + 400*x**5
# a = 0     b=0.8       result=1.640533
from AV1.trapezoidal_method import trapm

f = '0.2 + 25*x - 200*x**2 + 675*x**3 - 900*x**4 + 400*x**5'

def simp13(h, y0, y1, y2):
    """
        Single-application simpson 1/3
    :param h:
    :param y0:
    :param y1:
    :param y2:
    :return:
    """
    Simp13 = 2*h* (y0+4*y1+y2) / 6
    return Simp13


def simp13m(h, n, f):
    """
        Multiple-application simpson 1/3
    :param h:
    :param n:
    :param f:
    :return:
    """
    x = 0; sum = eval(f)
    for i in range(1, n-1, 2):
        x = i*h ; fi = eval(f)
        x = (i +1)*h ; fi2 = eval(f)
        sum = sum + 4*fi + 2 * fi2
    x = h * (n - 1); fn1 = eval(f)
    x = h * (n) ; fn = eval(f)
    sum = sum + 4 * fn1 + fn
    Simp13m = h * sum / 3
    return Simp13m

def simp38(h, f0, f1, f2, f3):
    """
        Single-application simpson 3/8
    :param h:
    :param f0:
    :param f1:
    :param f2:
    :param f3:
    :return:
    """
    Simp38 = 3*h* ( f0 + 3*(f1+f2) +f3 ) / 8
    return Simp38

def simp38m(startpoint, endpoint, n , f):
    h = (endpoint - startpoint) / n
    sum = 0.0
    for i in range(0, n, 1):
        aux_h = h / (3)
        x = startpoint + (i*h) ; fi = eval(f)
        x = (startpoint + (i*h)) + aux_h ; fi2 = eval(f)
        x = (startpoint + (i*h)) + (2 * aux_h) ; fi3 = eval(f)
        x = startpoint+(i +1)*h ; fi4 = eval(f)
        sum = sum + simp38(aux_h,fi, fi2, fi3, fi4 )
    Simp38m = sum
    return Simp38m

def simpInt(a, b, n, f):
    """
        Multiple application for odd and even number of segments.
    :param a:
    :param b:
    :param n:
    :param f:
    :return:
    """

    h = (b-a)/n
    sum = 0
    if n == 1:
        x = (n-1) * h ; fn_minus = eval(f)
        x = (n) * h ; fn = eval(f)
        sum = trapm(f, h, fn_minus, fn)
    else:
        m = n
        odd = (n/2) - int(n/2)
        if (odd > 0) and (n > 1):
            x= (n-3) * h ; fn_minus3 = eval(f)
            x= (n-2) * h ; fn_minus2 = eval(f)
            x= (n-1) * h ; fn_minus1 = eval(f)
            x= (n) * h ; fn_minus0 = eval(f)
            sum = sum + simp38(h, fn_minus3, fn_minus2, fn_minus1, fn_minus0)
            m = n -3
        if m > 1:
            sum = sum + simp13m(h, m, f)
    SimInt = sum
    return SimInt

def calculate_simp38_single():
    y_list = list()
    n = 1
    h = (0.8 - 0.0) / (n * (4 - 1))
    i = 0
    while i < n:
        x = i;
        y_list.append(eval(f))
        i += h

    print(simp38(h, y_list[0], y_list[1], y_list[2], y_list[3]))

def calculate_simp13_single():
    y_list = list()
    n = 1
    h = (0.8 - 0.0) / (n * (3 - 1))
    i = 0
    while i < n:
        x = i;
        y_list.append(eval(f))
        i += h
    print(simp13(h,y_list[0],y_list[1],y_list[2] ))

def calculate_simp13_multiple(startpoint, endpoint, n,  f):
    h = (endpoint - startpoint) / n
    return simp13m(h, n, f)

# print(simp38m(0.0, 0.8, 4, f))
# print(calculate_simp13_multiple(0.0, 0.8, 10, f))