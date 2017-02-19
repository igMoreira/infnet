def trapezoid_method(F):
    f = str(F)
    print(trapm(f,10,0, 0.8))

def trapm(f, n, startpoint, endpoint):
    x=startpoint
    h= (endpoint- startpoint)/n

    sum=eval(f)
    i= 1
    while i < n:
        x = x + h
        sum +=  2 * eval(f)
        i+=1
    x=endpoint
    sum += eval(f)
    Trapm = h * sum/2
    return Trapm

# trapezoid_method('0.2 + 25*x - 200*x**2 + 675*x**3 - 900*x**4 + 400*x**5')
