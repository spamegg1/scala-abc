import math.*

val EPSILON = 0.00000001

def bisectionCubeRoot(cube: Double) =
  // Approximates squareroot of positive input within epsilon accuracy.
  var left = 0.0
  var right = 0.0

  if cube >= 0 then
    left = 0
    right = cube
  else
    left = cube
    right = 0

  var guess = (right + left) / 2
  var diff = pow(guess, 3) - cube

  while
    abs(diff) >= EPSILON
  do
    if diff >= 0 then
      right = guess
    else
      left = guess
    guess = (right + left) / 2
    diff = pow(guess, 3) - cube

  if abs(diff) >= EPSILON then
    println(s"failed ${pow(guess, 3)}")
  else
    print(s"succeeded: ${guess}")

bisectionCubeRoot(-216.0)
