# y = a0 + a1 * x + e
# e = y − a0 − a1 * x

from math import sqrt
import numpy as np
import matplotlib.pyplot as plt

# X = [1, 2, 3, 4, 5, 6, 7]
# Y = [0.5, 2.5, 2.0, 4.0, 3.5, 6.0, 5.5]

X = [1, 2, 3, 4, 5, 6, 7,8,9,10,11,12,13,14,15]
Y = [10.00, 16.30 , 23.00, 27.50, 31.00, 35.60, 39.00, 41.50, 42.90, 45.00, 46.00, 45.50, 46.00, 49.00, 50.00]

def calculate_a1(x, y):
    """
    calculates a1
    :param x:
    :param y:
    :return:
    """
    X = list(x)
    Y = list(y)
    n = len(X)

    sumX_Y=0.0
    sumX=0.0
    sumY=0.0
    sumX_Sqr=0.0

    for i in range(0, len(X)):
        sumX_Y += ( X[i] * Y[i] )
        sumX += X[i]
        sumY += Y[i]
        sumX_Sqr += pow(X[i], 2)
    numerator = ( n*sumX_Y - ( sumX * sumY ) )
    denominator = (n*sumX_Sqr - (pow(sumX,2)) )
    a1 = numerator/denominator
    return a1

def calculate_a0(X, Y, a1):
    """
    Calculates a0
    :param X:
    :param Y:
    :param a1:
    :return:
    """
    y_means = sum(Y) / len(Y)
    x_means = sum(X) / len(X)
    a0 = y_means - a1*x_means
    return a0

def calculate_error(x, y, a1, a0):
    """
    Calculates standard deviation and improvement rate.
    :param x:
    :param y:
    :param a1:
    :param a0:
    :return:
    """
    Y = list(y)
    X = list(x)
    n = len(Y)
    st = 0
    sr = 0
    ym = sum(Y)/n
    for i in range(0, n):
        st += pow((Y[i] - ym), 2)
        sr += pow((Y[i] - a1*X[i] - a0), 2)
    syx= sqrt((sr/(n - 2)))
    r2= (st - sr) / st
    return (syx, r2)

def plot_chart(formula, x_range, a1, a0):
    """
    Draws a chart.
    :param formula:
    :param x_range:
    :param a1:
    :param a0:
    :return:
    """
    x = np.array(x_range)
    y = eval(formula)
    plt.plot(x, y, linewidth=2)
    plt.scatter(X,Y, color='red')
    plt.show()


def linear_regression():
    """
    Calculates a normalized function giving some points, using linear regression.
    :return:
    """
    a1 = calculate_a1(X, Y)
    a0 = calculate_a0(X,Y,a1)
    calculate_error(X,Y,a1,a0)
    plot_chart('a1*x +a0', range(min(X), max(X)+2), a1, a0)

linear_regression()