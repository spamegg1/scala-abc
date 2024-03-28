import math.*

def evalQuadratic(a: Double, b: Double, c: Double, x: Double) =
  // a, b, c: numerical values for the coefficients of a quadratic equation
  // x: numerical value at which to evaluate the quadratic.
  // Your code here
  a * x * x + b * x + c

evalQuadratic(1.2, 3.4, 5.6, 7)
