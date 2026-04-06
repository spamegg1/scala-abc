val x = 0.5
var p: Int = 0

while (math.pow(2, p) * x) % 1 != 0 do
  println(s"Remainder = ${math.pow(2, p) * x - (math.pow(2, p) * x).toInt}")
  p += 1

var num = (math.pow(2, p) * x).toInt
var result = ""

if num == 0 then result = "0"

while num > 0 do
  result = (num % 2).toString + result
  num = num / 2

for i <- 0 until p - result.length do result = "0" + result

result = result.take(p - result.length) + "." + result.drop(p - result.length)
println(s"The binary representation of the decimal ${x} is ${result}")
