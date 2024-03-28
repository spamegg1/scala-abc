import math.*

val x = 25
val epsilon = 0.01
var numGuesses = 0

var low = 0.0
var high = x.toDouble
var ans = (high + low) / 2.0

while
  abs(pow(ans, 2) - x) >= epsilon
do
  println(s"low = ${low}  high = ${high}  ans = ${ans}")
  numGuesses += 1
  if pow(ans, 2) < x then
    low = ans
  else
    high = ans
  ans = (high + low) / 2.0

println(s"number of guesses = ${numGuesses}")
println(s"${ans} is close to square root of ${x}")
