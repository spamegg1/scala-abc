import math.*

val EPSILON = 0.00000001

def bisectionSqrt(square: Double) =
  // Approximates squareroot of positive input within epsilon accuracy.
  require(square >= 0)

  var left = 0.0
  var right = square
  var guess = (right + left) / 2
  var diff = pow(guess, 2) - square

  while
    abs(diff) >= EPSILON
  do
    if diff >= 0 then
      right = guess
    else
      left = guess
    guess = (right + left) / 2
    diff = pow(guess, 2) - square

  if abs(diff) >= EPSILON then
    println(s"failed ${pow(guess, 2)}")
  else
    print(s"succeeded: ${guess}")

bisectionSqrt(4096)
