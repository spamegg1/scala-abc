def gcdIter(a: Int, b: Int): Int =
  // a, b: positive integers
  // returns: a positive integer, the greatest common divisor of a & b.
  var guess = math.min(a, b)
  while !(a % guess == 0 && b % guess == 0) && guess > 1 do guess -= 1
  guess

gcdIter(177, 99)
