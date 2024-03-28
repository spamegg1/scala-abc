import math.*

val epsilon = 0.01
val y = 54.0
var guess = y / 2.0
var numGuesses = 0

while
  abs(pow(guess, 2) - y) >= epsilon
do
  numGuesses += 1
  guess = guess - (pow(guess, 2) - y)/ (2 * guess)

println(s"number of guesses = ${numGuesses}")
println(s"Square root of ${y} is about ${guess}")
