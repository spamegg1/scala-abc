import math.*

val cube = 54
val epsilon = 0.01
var numGuesses = 0
var low = 0.0
var high = cube.toDouble
var guess = (high + low) / 2.0

while
  abs(pow(guess, 3) - cube) >= epsilon
do
  if pow(guess, 3) < cube then
    low = guess
  else
    high = guess
  guess = (high + low) / 2.0
  numGuesses += 1
println(s"number of guesses = ${numGuesses}")
println(s"${guess} is close to the cube root of ${cube}")
