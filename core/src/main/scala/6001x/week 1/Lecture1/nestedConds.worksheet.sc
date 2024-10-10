val x = 8

if x % 2 == 0 then
  if x % 3 == 0 then "Divisible by 2 and 3"
  else "Divisible by 2 and not by 3"
else if x % 3 == 0 then "Divisible by 3 and not by 2"
else "Not divisible by 2 or 3"
