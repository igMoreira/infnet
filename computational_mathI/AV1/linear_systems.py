#!/usr/bin/env python3

import sys

# b = [636,518,307]
# a = [[70,1,0],[60,-1,1],[40,0,-1]]
# x = [0,0,0]



if (len(sys.argv) - 1) != 2:
    print ('ERROR: INVALID USAGE')
    print ('Correct syntax: linear_systems.py file alg_number')
    print ('alg_number:')
    print (' 1- gauss elimination')
    print (' 2- jacobi')
    print (' 3- gauss-seidel')
    sys.exit()

alg = 1
try:
    alg = int(sys.argv[2])
except:
    print('Invalid number!!!')
    sys.exit()

try:
    with open(sys.argv[1], 'r') as f:
        lineTest = len(f.readline().split(sep=',')) - 1
        if sum(1 for _ in f) != lineTest:
            print ('Invalid system, the matrix must be squared.')
            sys.exit()
        else:
            for line in f:
                numbers = f.readline().split(sep=',')
except:
    print ('Invalid file. Please verify path!!!')
    sys.exit()

b = [11,13]
a = [[2,1],[5,7]]
x = [0,0]