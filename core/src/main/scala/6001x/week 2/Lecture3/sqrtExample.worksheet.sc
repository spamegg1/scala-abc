import scala.io.StdIn.readLine
import scala.math.*

var ans = 0
var negative = false

//val x = readLine("Enter an integer: ").toInt
val x = 4

if x < 0 then negative = true

while pow(ans, 2) < x
do ans = ans + 1

if pow(ans, 2) == x then println(s"Square root of ${x} is ${ans}")
else
  println(s"${x} is not a perfect square")
  if negative then println(s"Just checking... did you mean ${-x}?")
