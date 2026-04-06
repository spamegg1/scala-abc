val epsilon = 0.01
val y = 54.0
var guess = y / 2.0
var numGuesses = 0

while math.abs(math.pow(guess, 2) - y) >= epsilon do
  numGuesses += 1
  guess = guess - (math.pow(guess, 2) - y) / (2 * guess)

s"number of guesses = ${numGuesses}"
s"Square root of ${y} is about ${guess}"
