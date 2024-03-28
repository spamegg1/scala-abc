import math.*

var num: Int = 19
var isNeg = false

if num < 0 then
  isNeg = true
  num = abs(num)
else
  isNeg = false

var result = ""

if num == 0 then
  result = "0"

while
  num > 0
do
  result = (num % 2).toString + result
  num = num / 2
if isNeg then
  result = "-" + result

println(result)
