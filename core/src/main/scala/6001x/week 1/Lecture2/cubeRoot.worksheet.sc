val x = 28

var ans = 0
while math.pow(ans, 3) < x
do ans = ans + 1

if math.pow(ans, 3) != x then print(s"${x} is not a perfect cube")
else print(s"Cube root of ${x} is ${ans}")
