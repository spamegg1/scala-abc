val x = 5
var ans = 0
var itersLeft = x

while
  itersLeft != 0
do
  ans = ans + x
  itersLeft = itersLeft - 1

println(s"${x}*${x} = ${ans}")
