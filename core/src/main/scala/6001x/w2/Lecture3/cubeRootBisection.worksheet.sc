val cube = 54
val epsilon = 0.01
var numGuesses = 0
var low = 0.0
var high = cube.toDouble
var guess = (high + low) / 2.0

while math.abs(math.pow(guess, 3) - cube) >= epsilon do
  if math.pow(guess, 3) < cube then low = guess else high = guess
  guess = (high + low) / 2.0
  numGuesses += 1
s"number of guesses = ${numGuesses}"
s"${guess} is close to the cube root of ${cube}"
