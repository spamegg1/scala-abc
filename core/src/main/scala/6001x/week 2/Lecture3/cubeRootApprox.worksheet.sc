import math.*

// try with cube = 27, and large step (e.g. 2.0)
val cube = 27
val epsilon = 0.01
var guess = 0.0
val increment = 0.1
var numGuesses = 0

while
  abs(pow(guess, 3) - cube) >= epsilon && guess <= cube
do
  guess += increment
  numGuesses += 1

println(s"number of guesses = ${numGuesses}")

if abs(pow(guess, 3) - cube) >= epsilon then
  println(s"Failed on cube root of ${cube}")
else
  print(s"${guess} is close to the cube root of ${cube}")
