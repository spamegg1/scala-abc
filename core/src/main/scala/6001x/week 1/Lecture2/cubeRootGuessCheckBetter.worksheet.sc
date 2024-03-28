import math.*
import util.control.Breaks.*

val cube = -27
var best = 0

breakable(
  for
    guess <- 0 to abs(cube)
  do
    if pow(guess, 3) >= abs(cube) then
      best = guess
      break
)

if pow(best, 3) != abs(cube) then
  println(s"${cube} is not a perfect cube")
else
  if cube < 0 then
    best = -best
  println(s"Cube root of ${cube} is ${best}")
