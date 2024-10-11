val EPSILON = 0.00000001

def bisectionSqrt(square: Double) =
  // Approximates squareroot of positive input within epsilon accuracy.
  require(square >= 0)

  var left = 0.0
  var right = square
  var guess = (right + left) / 2
  var diff = math.pow(guess, 2) - square

  while math.abs(diff) >= EPSILON do
    if diff >= 0 then right = guess else left = guess
    guess = (right + left) / 2
    diff = math.pow(guess, 2) - square

  if math.abs(diff) >= EPSILON then println(s"failed ${math.pow(guess, 2)}")
  else print(s"succeeded: ${guess}")

bisectionSqrt(4096)
