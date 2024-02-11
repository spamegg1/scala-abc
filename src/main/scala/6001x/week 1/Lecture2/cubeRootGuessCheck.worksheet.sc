import math.*

val cube = 27
for
  guess <- 0 to cube
do
  if pow(guess, 3) == cube then
    println(s"Cube root of ${cube} is ${guess}")
