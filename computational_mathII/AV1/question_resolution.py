# Question 21.19 page 527

from AV1.romberg_method import execute_romberg
from AV1.simpson_method import simpInt
from AV1.trapezoidal_method import trapm

f = '5 + 0.25*x**2'
startpoint = 0.0
endpoint = 11

print("Question A )")
execute_romberg(f, startpoint, endpoint, 10, 10**-6)
print()

print("Question B )")
print("Results: " + str(trapm(f, 5, startpoint, endpoint)))
print()

print("Question C)")
print("Results: " + str(simpInt(startpoint, endpoint, 5, f)))
print()

print("Question D) ")
print("Trapezoidal method with 1m : ")
print("Results: " + str(trapm(f, 11, startpoint, endpoint)))
print()
print("Simpson method with 1m : ")
print("Results: " + str(simpInt(startpoint, endpoint, 11, f)))
print()