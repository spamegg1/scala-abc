val x = -4

var ans = 0
var negative = false

if x < 0 then negative = true

while math.pow(ans, 2) < x do ans = ans + 1

if math.pow(ans, 2) == x then println(s"Square root of ${x} is ${ans}")
else
  println(s"${x} is not a perfect square")
  if negative then println(s"Just checking... did you mean ${-x}?")
