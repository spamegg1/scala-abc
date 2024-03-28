import util.control.Breaks.*

var mySum = 0

breakable(
  for
    i <- 5 until 11 by 2
  do
    mySum += i
    if mySum == 5 then
      break
)

println(mySum)
