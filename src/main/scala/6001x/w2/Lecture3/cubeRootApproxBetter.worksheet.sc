val cube = 27
val epsilon = 0.01
var guess = 0.0
val increment = 0.0001
var numGuesses = 0

while math.abs(math.pow(guess, 3) - cube) >= epsilon do
  guess += increment
  numGuesses += 1

s"numGuesses = ${numGuesses}"

if math.abs(math.pow(guess, 3) - cube) >= epsilon then s"Failed on cube root of ${cube}"
else s"${guess} is close to the cube root of ${cube}"
