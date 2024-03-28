def square(x: Double) = x * x

def fourthPower(x: Double) =
  // x: int or float.
  // Your code here
  square(square(x))

fourthPower(2)
