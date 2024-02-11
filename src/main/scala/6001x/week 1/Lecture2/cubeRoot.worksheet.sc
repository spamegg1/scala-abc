import math.*
import scala.io.StdIn.readLine

//val x = readLine("Enter an integer: ")
val x = 27

var ans = 0
while pow(ans, 3) < x
do ans = ans + 1

if pow(ans, 3) != x then print(s"${x} is not a perfect cube")
else print(s"Cube root of ${x} is ${ans}")
