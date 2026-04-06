val cube = -729

var best = 0
var guess = 0
val bound = math.abs(cube)

while guess < bound do
  if math.pow(guess, 3) >= bound then
    best = guess
    guess = bound // break
  else guess += 1

if math.pow(best, 3) != bound then println(s"${cube} is not a perfect cube")
else
  if cube < 0 then best = -best
  println(s"Cube root of ${cube} is ${best}")
