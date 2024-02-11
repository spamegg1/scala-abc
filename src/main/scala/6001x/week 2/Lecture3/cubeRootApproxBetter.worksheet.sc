import math.*

val cube = 27
val epsilon = 0.01
var guess = 0.0
val increment = 0.0001
var numGuesses = 0

while
  abs(pow(guess, 3) - cube) >= epsilon
do
  guess += increment
  numGuesses += 1

println(s"numGuesses = ${numGuesses}")

if abs(pow(guess, 3) - cube) >= epsilon then
  println(s"Failed on cube root of ${cube}")
else
  println(s"${guess} is close to the cube root of ${cube}")
